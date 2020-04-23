package com.tohsoft.airquality.ui.demo.free.history;

import com.tohsoft.airquality.ui.base.MvpPresenter;

public interface DemoDataHistoryMvpPresenter<V extends DemoDataHistoryMvpView> extends MvpPresenter<V> {
    void loadData();
}
