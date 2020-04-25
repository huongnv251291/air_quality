package com.tohsoft.airquality.ui.demo.free.forecast;

import com.tohsoft.airquality.data.models.aqicn.ForecastModel;
import com.tohsoft.airquality.ui.base.BaseMvpView;

public interface DemoDataForeCastMvpView extends BaseMvpView {
    void updateCurrent(ForecastModel jsonObject);

    void requestFail();
}
