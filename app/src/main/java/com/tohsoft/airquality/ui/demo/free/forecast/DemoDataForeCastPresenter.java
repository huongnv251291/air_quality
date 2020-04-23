package com.tohsoft.airquality.ui.demo.free.forecast;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.tohsoft.airquality.data.ApplicationModules;
import com.tohsoft.airquality.data.network.DataManager;
import com.tohsoft.airquality.forecast.ForecastModel;
import com.tohsoft.airquality.ui.base.BasePresenter;

import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class DemoDataForeCastPresenter<V extends DemoDataForeCastMvpView> extends BasePresenter<V> implements DemoDataForeCastMvpPresenter<V> {
    DataManager dataManager;

    public DemoDataForeCastPresenter(Context context) {
        super(context);
        dataManager = ApplicationModules.getInstant().getDataManager();
    }

    @Override
    public void loadData() {
        if (ApplicationModules.getInstant().getStaticData() != null) {
            updateForeCast(ApplicationModules.getInstant().getStaticData());
            return;
        }
        mCompositeDisposable.add(dataManager.getForecastData(mContext, "_C8tMLclLzNX3SMzLz9QPDXbNTUosLq4EAA", "Vietnam%2FHanoi%2FUSEmbassy").observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                Log.e("getForecastData", responseBody.toString());
                JSONObject jsonObject = new JSONObject(responseBody.string());
                ApplicationModules.getInstant().updateStaticData(jsonObject);
                if (requestFail(jsonObject)) {
                    getMvpView().requestFail();
                } else {
                    updateForeCast(jsonObject);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();

            }
        }));
    }

    private void updateForeCast(JSONObject jsonObject) {
        JSONObject object = jsonObject.optJSONObject("forecast");
        if (object != null) {
            ForecastModel forecastList = ForecastModel.decode(object);
            getMvpView().updateCurrent(forecastList);
        }
    }

    private boolean requestFail(JSONObject jsonObject) {
        return TextUtils.equals(jsonObject.optString("data", ""), "error");
    }
}
