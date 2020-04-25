package com.tohsoft.airquality.ui.demo.free.current;

import com.tohsoft.airquality.data.models.aqicn.AqiOfCity;
import com.tohsoft.airquality.ui.base.BaseMvpView;

public interface DemoDataCurrentMvpView extends BaseMvpView {
    void updateCurrent(AqiOfCity jsonObject);

    void requestFail();
}
