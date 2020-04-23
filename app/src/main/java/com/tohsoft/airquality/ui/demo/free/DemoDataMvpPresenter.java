package com.tohsoft.airquality.ui.demo.free;

import com.tohsoft.airquality.ui.base.MvpPresenter;

public interface DemoDataMvpPresenter<V extends DemoDataMvpView> extends MvpPresenter<V> {
    void loadData();
}
