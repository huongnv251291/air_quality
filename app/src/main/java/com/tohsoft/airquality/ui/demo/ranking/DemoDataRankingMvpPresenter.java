package com.tohsoft.airquality.ui.demo.ranking;

import com.tohsoft.airquality.ui.base.MvpPresenter;

public interface DemoDataRankingMvpPresenter<V extends DemoDataRankingMvpView> extends MvpPresenter<V> {
    void loadData();
}
