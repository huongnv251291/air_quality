package com.tohsoft.airquality.ui.demo.forecast;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.tohsoft.airquality.R;
import com.tohsoft.airquality.forecast.ForecastModel;
import com.tohsoft.airquality.ui.base.BaseFragmentSetup;
import com.tohsoft.airquality.ui.base.BasePresenter;

import butterknife.BindView;

public class FragmentDemoDataForeCast extends BaseFragmentSetup<DemoDataForeCastMvpPresenter> implements DemoDataForeCastMvpView {
    @BindView(R.id.list_ranking)
    RecyclerView recyclerView;

    @Override
    protected BasePresenter onRegisterPresenter() {
        return new DemoDataForeCastPresenter(mContext);
    }

    @Override
    public Integer getIdBg() {
        return null;
    }

    @Override
    public int getIdLayout() {
        return R.layout.frm_demo_ranking;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mPresenter.loadData();
    }

    @Override
    public void updateCurrent(ForecastModel jsonObject) {

    }

    @Override
    public void requestFail() {

    }
}
