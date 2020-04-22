package com.tohsoft.airquality.ui.demo.history;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewpager2.widget.ViewPager2;

import com.tohsoft.airquality.R;
import com.tohsoft.airquality.ui.base.BaseFragmentSetup;
import com.tohsoft.airquality.ui.base.BasePresenter;

import butterknife.BindView;

public class FragmentDemoDataHistory extends BaseFragmentSetup<DemoDataHistoryMvpPresenter> implements DemoDataHistoryMvpView {
    @BindView(R.id.title_demo)
    AppCompatTextView title;
    @BindView(R.id.container_demo)
    ViewPager2 viewPager2;

    @Override
    protected BasePresenter onRegisterPresenter() {
        return new DemoDataHistoryPresenter(mContext);
    }

    @Override
    public Integer getIdBg() {
        return null;
    }

    @Override
    public int getIdLayout() {
        return R.layout.frm_demo_aqi;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mPresenter.loadData();
    }

    @Override
    public void requestFail() {

    }
}
