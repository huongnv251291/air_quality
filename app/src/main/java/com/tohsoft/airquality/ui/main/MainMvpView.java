package com.tohsoft.airquality.ui.main;


import com.tohsoft.airquality.ui.base.BaseMvpView;
import com.tohsoft.airquality.utils.ads.InterstitialOPAHelper;

/**
 * Created by Phong on 2/2/2017.
 */

public interface MainMvpView extends BaseMvpView, InterstitialOPAHelper.InterstitialOPAListener {

    void checkAndShowFullScreenQuitApp();
}
