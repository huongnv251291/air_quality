package com.tohsoft.airquality.ui.demo.free.ranking;

import com.tohsoft.airquality.data.models.RankingCountry;
import com.tohsoft.airquality.ui.base.BaseMvpView;

public interface DemoDataRankingMvpView extends BaseMvpView {
    void updateCurrent(RankingCountry rankingCountry);

    void requestFail();
}
