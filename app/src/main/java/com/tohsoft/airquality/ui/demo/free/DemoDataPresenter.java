package com.tohsoft.airquality.ui.demo.free;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.text.TextUtils;
import android.util.Log;

import com.tohsoft.airquality.data.ApplicationModules;
import com.tohsoft.airquality.data.models.RoundMap;
import com.tohsoft.airquality.data.network.DataManager;
import com.tohsoft.airquality.forecast.ForecastModel;
import com.tohsoft.airquality.ui.base.BasePresenter;

import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class DemoDataPresenter<V extends DemoDataMvpView> extends BasePresenter<V> implements DemoDataMvpPresenter<V> {
    DataManager dataManager;

    public DemoDataPresenter(Context context) {
        super(context);
        dataManager = ApplicationModules.getInstant().getDataManager();
    }

    @Override
    public void loadData() {

        mCompositeDisposable.add(dataManager.getForecastData(mContext, "_C8tMLclLzNX3SMzLz9QPDXbNTUosLq4EAA", "Vietnam%2FHanoi%2FUSEmbassy").observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                Log.e("getForecastData", responseBody.toString());
                    JSONObject jsonObject = new JSONObject(responseBody.string());
                    if (requestFail(jsonObject)) {
                        getMvpView().requestFail();
                    } else {
                        JSONObject object = jsonObject.optJSONObject("forecast");
                        if (object != null) {
                            ForecastModel forecastList = ForecastModel.decode(object);
                            Log.e("getForecastData",forecastList.toString());
                        }
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();

            }
        }));
//        20.9968,105.8408,41.771312,118.285797
        Location a = new Location(LocationManager.GPS_PROVIDER);
        a.setLatitude(20.9968);
        a.setLongitude(105.8408);
        Location b = new Location(LocationManager.GPS_PROVIDER);
        b.setLatitude(41.771312);
        b.setLongitude(118.285797);
        mCompositeDisposable.add(dataManager.getMapRound(a,b).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<RoundMap>() {
            @Override
            public void accept(RoundMap responseBody) throws Exception {
                Log.e("getMapRound", responseBody.toString());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                    throwable.printStackTrace();
            }
        }));
    }
    private boolean requestFail(JSONObject jsonObject) {
        return TextUtils.equals(jsonObject.optString("data", ""), "error");
    }
}
