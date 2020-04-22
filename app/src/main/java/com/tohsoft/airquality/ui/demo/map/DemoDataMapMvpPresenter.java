package com.tohsoft.airquality.ui.demo.map;

import com.google.android.gms.maps.GoogleMap;
import com.tohsoft.airquality.ui.base.MvpPresenter;

public interface DemoDataMapMvpPresenter<V extends DemoDataMapMvpView> extends MvpPresenter<V> {
    void loadData();

    void setMap(GoogleMap googleMap);

}
