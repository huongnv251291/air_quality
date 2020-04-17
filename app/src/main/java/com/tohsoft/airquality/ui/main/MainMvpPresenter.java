package com.tohsoft.airquality.ui.main;

import com.tohsoft.airquality.ui.base.MvpPresenter;

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void initData();

    void checkRateDialogStopped();
}
