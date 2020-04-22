package com.tohsoft.airquality.ui.demo.current;

import com.tohsoft.airquality.ui.base.MvpPresenter;
import com.tohsoft.airquality.ui.main.MainMvpView;

public interface DemoDataCurrentMvpPresenter<V extends DemoDataCurrentMvpView> extends MvpPresenter<V> {
    void loadData();
}
