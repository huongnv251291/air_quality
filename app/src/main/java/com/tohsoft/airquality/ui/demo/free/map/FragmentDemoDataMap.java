package com.tohsoft.airquality.ui.demo.free.map;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.tohsoft.airquality.R;
import com.tohsoft.airquality.ui.base.BaseFragmentSetup;
import com.tohsoft.airquality.ui.base.BasePresenter;

import butterknife.BindView;

public class FragmentDemoDataMap extends BaseFragmentSetup<DemoDataMapMvpPresenter> implements DemoDataMapMvpView, OnMapReadyCallback {
    @BindView(R.id.title_demo)
    AppCompatTextView title;


    @Override
    protected BasePresenter onRegisterPresenter() {
        return new DemoDataMapMapPresenter(mContext);
    }

    @Override
    public Integer getIdBg() {
        return null;
    }

    @Override
    public int getIdLayout() {
        return R.layout.frm_demo_map;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void requestFail() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mPresenter.setMap(googleMap);
        mPresenter.loadData();
    }
}
