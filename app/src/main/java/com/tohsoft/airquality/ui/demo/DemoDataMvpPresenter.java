package com.tohsoft.airquality.ui.demo;

import com.tohsoft.airquality.ui.base.MvpPresenter;
import com.tohsoft.airquality.ui.main.MainMvpView;

public interface DemoDataMvpPresenter<V extends DemoDataMvpView> extends MvpPresenter<V> {
    void loadData();
}
