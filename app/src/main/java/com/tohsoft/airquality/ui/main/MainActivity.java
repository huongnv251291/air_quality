package com.tohsoft.airquality.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.gms.ads.MobileAds;
import com.tohsoft.airquality.BaseApplication;
import com.tohsoft.airquality.BuildConfig;
import com.tohsoft.airquality.R;
import com.tohsoft.airquality.data.ApplicationModules;
import com.tohsoft.airquality.data.local.preference.PreferencesHelper;
import com.tohsoft.airquality.helper.FirebaseRemoteConfigHelper;
import com.tohsoft.airquality.services.BackgroundService;
import com.tohsoft.airquality.ui.base.BaseActivity;
import com.tohsoft.airquality.ui.base.BasePresenter;
import com.tohsoft.airquality.ui.demo.free.ActivityFree;
import com.tohsoft.airquality.ui.demo.free.current.FragmentDemoDataCurrent;
import com.tohsoft.airquality.ui.demo.free.forecast.FragmentDemoDataForeCast;
import com.tohsoft.airquality.ui.demo.free.history.FragmentDemoDataHistory;
import com.tohsoft.airquality.ui.demo.free.map.FragmentDemoDataMap;
import com.tohsoft.airquality.ui.demo.free.ranking.FragmentDemoDataRanking;
import com.tohsoft.airquality.ui.demo.pay.ActivityPay;
import com.tohsoft.airquality.utils.AutoStartManagerUtil;
import com.tohsoft.airquality.utils.ads.AdViewWrapper;
import com.tohsoft.airquality.utils.ads.AdsConstants;
import com.tohsoft.airquality.utils.ads.Advertisements;
import com.tohsoft.airquality.utils.ads.InterstitialOPAHelper;
import com.tohsoft.airquality.utils.commons.Communicate;
import com.tohsoft.airquality.utils.language.LocaleManager;
import com.tohsoft.airquality.utils.xiaomi.Miui;
import com.tohsoft.lib.AppSelfLib;
import com.utility.RuntimePermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity<MainMvpPresenter> implements MainMvpView {
    @BindView(R.id.fr_bottom_banner)
    FrameLayout frBottomBanner;
    @BindView(R.id.fr_splash)
    View frSplash;
    @BindView(R.id.iv_splash)
    ImageView ivSplash;
    @BindView(R.id.ll_fake_progress)
    View llFakeProgress;
    @BindView(R.id.iv_warning)
    View ivWarning;
    //    @BindView(R.id.holder_demo)
//    ViewPager viewPager;
    private InterstitialOPAHelper mInterstitialOPAHelper;
    private AdViewWrapper mAdViewWrapper;
    private AlertDialog mDialogExitApp;
    private MaterialDialog mDialogGetProVersion;

    @Override
    protected BasePresenter onRegisterPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected ViewGroup getBottomAdsContainer() {
        return frBottomBanner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppSelfLib.language = LocaleManager.getLocale(getResources()).getLanguage();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        FirebaseRemoteConfigHelper.getInstance().fetchRemoteData(mContext);

        setSplashMargin();
        initAds();

        checkAutoStartManager();

        checkStartInBackgroundPermission();
    }

    /*
     * Check and init Ads
     * */
    private void initAds() {
        if (BuildConfig.SHOW_AD) {
            frSplash.setVisibility(View.VISIBLE);

            // Initialize Ads
            MobileAds.initialize(mContext, getString(R.string.admob_app_id));

            // OPA
            mInterstitialOPAHelper = new InterstitialOPAHelper(getContext(), llFakeProgress, this);
            mInterstitialOPAHelper.initInterstitialOpenApp();
            // Others (Banner, Gift, EmptyScreen...)
            new Handler().postDelayed(() -> {
                // AdView exit dialog
                mAdViewWrapper = new AdViewWrapper();
                mAdViewWrapper.initBannerExitDialog(mContext, null);
            }, 2000);
        } else {
            checkPermissions();
        }
    }

    /**
     * Fix lỗi co kéo ảnh splash so với ảnh window background
     */
    private void setSplashMargin() {
        if (ivSplash != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) ivSplash.getLayoutParams();
            layoutParams.topMargin = -BarUtils.getStatusBarHeight();
            if (BarUtils.isSupportNavBar()) {
                layoutParams.bottomMargin = -BarUtils.getNavBarHeight();
            }
            ivSplash.setLayoutParams(layoutParams);
        }
    }

    /**
     * Kiểm tra và xin cấp quyền chạy service khi app bị kill trên một số dòng máy
     * <p>
     * Start service sau method này {@link BackgroundService}
     */
    private void checkAutoStartManager() {
        if (AutoStartManagerUtil.shouldShowEnableAutoStart(getContext())) {
            ivWarning.setVisibility(View.VISIBLE);
            /*if (AutoStartManagerUtil.canShowWarningIcon(getContext())) {
                // Hiển thị icon warning
            } else {
                // Ẩn icon warning và hiển thị chỗ khác
            }*/
        } else {
            ivWarning.setVisibility(View.GONE);
        }
    }

    /**
     * Kiểm tra và hiển thị pop-up gợi ý users mua bản PRO
     */
    private void checkAndShowGetProVersion() {
        if (mDialogGetProVersion != null && mDialogGetProVersion.isShowing()) {
            return;
        }
        if (FirebaseRemoteConfigHelper.getInstance().getProVersionEnable() &&
                ApplicationModules.getInstant().getPreferencesHelper().canShowGetProVersion()) {
            MaterialDialog.Builder builder = new MaterialDialog.Builder(mContext)
                    .content(R.string.lbl_get_pro_version_title)
                    .negativeText(R.string.action_later)
                    .positiveText(R.string.action_ok_buy_now)
                    .onPositive((dialog, which) -> {
                        ApplicationModules.getInstant().getPreferencesHelper().setGetProVersionEnable(false);
                        Communicate.getFullVersion(mContext);
                    })
                    .neutralText(R.string.action_no_thanks)
                    .onNeutral((dialog, which) -> {
                                ApplicationModules.getInstant().getPreferencesHelper().setGetProVersionEnable(false);
                            }
                    );

            mDialogGetProVersion = builder.build();
            mDialogGetProVersion.show();
        }
    }

    /**
     * Kiểm tra và xin cấp quyền StartInBackground khi startIntent như mở một app khác trên MIUI (Xiaomi devices)
     */
    private void checkStartInBackgroundPermission() {
        if (!PreferencesHelper.isStartInBackgroundShowed(mContext)) {
            Miui.requestStartInBackground(mContext);
        }
    }

    private MaterialDialog.SingleButtonCallback enableAutoStartListener = new MaterialDialog.SingleButtonCallback() {
        @Override
        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
            if (ivWarning != null) {
                ivWarning.setVisibility(View.GONE);
            }
        }
    };

    /*
     * Check runtime permissions & init data
     * */
    @SuppressLint("CheckResult")
    private void checkPermissions() {
        frSplash.setVisibility(View.GONE);
        // Check permission & request
        if (RuntimePermissions.checkAccessStoragePermission(mContext)) {
            mPresenter.initData();
        } else {
            RuntimePermissions.requestStoragePermission(mContext);
        }

        checkAndShowGetProVersion();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RuntimePermissions.RequestCodePermission.REQUEST_CODE_GRANT_STORAGE_PERMISSIONS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkPermissions();
            } else {
                ToastUtils.showLong(getString(R.string.msg_alert_storage_permission_denied));
            }
        }
    }

    @Override
    public void onAdOPACompleted() {
        checkPermissions();
    }

    @Override
    public void checkAndShowFullScreenQuitApp() {
        if (mInterstitialOPAHelper != null) {
            mInterstitialOPAHelper.checkAndShowFullScreenQuitApp();
        } else {
            showExitDialog();
        }
    }

    private void dismissExitDialog() {
        if (mDialogExitApp != null && mDialogExitApp.isShowing()) {
            mDialogExitApp.dismiss();
        }
    }

    @Override
    public void showExitDialog() {
        dismissExitDialog();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.msg_exit_app);
        @SuppressLint("InflateParams") View exitDialogView = getLayoutInflater().inflate(R.layout.dialog_exit_app, null);
        // Ads
        ViewGroup adsContainer = exitDialogView.findViewById(R.id.fr_ads_container_exit);
        Advertisements.addBannerAdsToContainer(adsContainer, mAdViewWrapper != null ? mAdViewWrapper.getAdView() : null);
        // Checkbox never show again
        CheckBox cbNeverShowAgain = exitDialogView.findViewById(R.id.cb_never_show_again);
        cbNeverShowAgain.setOnCheckedChangeListener((buttonView, isChecked) ->
                ApplicationModules.getInstant().getPreferencesHelper().setShowExitDialog(!isChecked));

        builder.setView(exitDialogView);
        builder.setPositiveButton(R.string.action_yes, (dialog, which) -> {
            mDialogExitApp.dismiss();
            finishApplication();
        });
        builder.setNegativeButton(R.string.action_cancel, (dialog, which) -> dialog.cancel());
        mDialogExitApp = builder.create();
        mDialogExitApp.show();
    }

    private void finishApplication() {
        new Handler().postDelayed(this::finish, 150);
    }

    @Override
    public void hideSplash() {
        if (frSplash != null) {
            frSplash.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        if (mInterstitialOPAHelper != null) {
            mInterstitialOPAHelper.onResume();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (mInterstitialOPAHelper != null) {
            mInterstitialOPAHelper.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        AdsConstants.destroy();
        BaseApplication.getInstance().clearAllRequest();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialOPAHelper != null && mInterstitialOPAHelper.isCounting()) {
            return;
        }
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            super.onBackPressed();
            return;
        }
        onQuitApp();
    }

    public void onQuitApp() {
        boolean isShowRateDialog = AppSelfLib.showRateActivityNewStyleHighScore(mContext, 1,
                Communicate.EMAIL_COMPANY, mContext.getString(R.string.app_name));
        if (isShowRateDialog) {
            dismissExitDialog();
            if (mPresenter != null) {
                mPresenter.checkRateDialogStopped();
            }
        } else {
            if (ApplicationModules.getInstant().getPreferencesHelper().canShowExitDialog()) {
                checkAndShowFullScreenQuitApp();
            } else {
                finish();
            }
        }
    }

    @OnClick({R.id.btn_ranking, R.id.btn_history, R.id.iv_warning})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ranking:
                startActivity(new Intent(this, ActivityFree.class));
                break;
            case R.id.btn_history:
                startActivity(new Intent(this, ActivityPay.class));
//                startActivity(new Intent(mContext, HistoryActivity.class));
                break;
            case R.id.iv_warning:
                AutoStartManagerUtil.showDialogEnableAutoStart(getContext(), enableAutoStartListener);
                break;
        }
    }
}
