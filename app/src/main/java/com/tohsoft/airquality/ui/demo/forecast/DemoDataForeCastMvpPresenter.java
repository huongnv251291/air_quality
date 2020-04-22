package com.tohsoft.airquality.ui.demo.forecast;

import com.tohsoft.airquality.ui.base.MvpPresenter;

public interface DemoDataForeCastMvpPresenter<V extends DemoDataForeCastMvpView> extends MvpPresenter<V> {
    void loadData();
}
