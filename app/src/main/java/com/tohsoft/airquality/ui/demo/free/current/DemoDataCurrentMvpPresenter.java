package com.tohsoft.airquality.ui.demo.free.current;

import com.tohsoft.airquality.ui.base.MvpPresenter;

public interface DemoDataCurrentMvpPresenter<V extends DemoDataCurrentMvpView> extends MvpPresenter<V> {
    void loadData();
}
