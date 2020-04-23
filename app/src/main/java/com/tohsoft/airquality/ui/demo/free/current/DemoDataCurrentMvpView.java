package com.tohsoft.airquality.ui.demo.free.current;

import com.tohsoft.airquality.data.models.AqiOfCity;
import com.tohsoft.airquality.ui.base.BaseMvpView;

public interface DemoDataCurrentMvpView extends BaseMvpView {
    void updateCurrent(AqiOfCity jsonObject);

    void requestFail();
}
