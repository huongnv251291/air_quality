package com.tohsoft.airquality.ui.demo.pay;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.tohsoft.airquality.BaseApplication;
import com.tohsoft.airquality.R;
import com.tohsoft.airquality.utils.language.LocaleManager;
import com.tohsoft.lib.AppSelfLib;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityPay extends AppCompatActivity {
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    CustomViewPager mViewPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppSelfLib.language = LocaleManager.getLocale(getResources()).getLanguage();
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        setupTabLayout();
    }

    private void setupTabLayout() {
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(this, getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        BaseApplication.getInstance().clearAllRequest();
        super.onDestroy();
    }

}
