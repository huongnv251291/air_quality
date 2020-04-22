package com.tohsoft.airquality.ui.demo.forecast;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tohsoft.airquality.R;
import com.tohsoft.airquality.forecast.ForecastModel;
import com.tohsoft.airquality.ui.base.BaseFragmentSetup;
import com.tohsoft.airquality.ui.base.BasePresenter;
import com.tohsoft.airquality.ui.demo.forecast.adapter.AdapterForecast;

import butterknife.BindView;

public class FragmentDemoDataForeCast extends BaseFragmentSetup<DemoDataForeCastMvpPresenter> implements DemoDataForeCastMvpView, View.OnClickListener {
    @BindView(R.id.list_ranking)
    RecyclerView recyclerView;
    private AdapterForecast adapterForecast;

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
         adapterForecast = new AdapterForecast();
        adapterForecast.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapterForecast);
        mPresenter.loadData();
    }

    @Override
    public void updateCurrent(ForecastModel jsonObject) {
        adapterForecast.setData(jsonObject.entries());
    }

    @Override
    public void requestFail() {

    }

    @Override
    public void onClick(View v) {

    }
}
