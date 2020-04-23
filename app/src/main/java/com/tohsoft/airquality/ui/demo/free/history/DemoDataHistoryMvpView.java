package com.tohsoft.airquality.ui.demo.free.history;

import com.tohsoft.airquality.data.models.GraphModel;
import com.tohsoft.airquality.ui.base.BaseMvpView;

import java.util.List;

public interface DemoDataHistoryMvpView extends BaseMvpView {
    void requestFail();

    void updateHistoryData(List<GraphModel> graphModels);
}
