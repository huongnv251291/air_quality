package com.tohsoft.airquality.ui.demo.free.current;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.tohsoft.airquality.data.ApplicationModules;
import com.tohsoft.airquality.data.models.AqiOfCity;
import com.tohsoft.airquality.data.network.DataManager;
import com.tohsoft.airquality.ui.base.BasePresenter;

import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class DemoDataCurrentPresenter<V extends DemoDataCurrentMvpView> extends BasePresenter<V> implements DemoDataCurrentMvpPresenter<V> {
    DataManager dataManager;

    public DemoDataCurrentPresenter(Context context) {
        super(context);
        dataManager = ApplicationModules.getInstant().getDataManager();
    }

    @Override
    public void loadData() {
        mCompositeDisposable.add(dataManager.getFeedByLocation(null).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                Log.e("getFeed", responseBody.toString());
                JSONObject jsonObject = new JSONObject(responseBody.string());
                if (requestFail(jsonObject)) {
                    getMvpView().requestFail();
                } else {
                    AqiOfCity aqiOfCity = new AqiOfCity();
                    aqiOfCity.convert(jsonObject);
                    getMvpView().updateCurrent(aqiOfCity);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
                getMvpView().requestFail();
            }
        }));
    }

    private boolean requestFail(JSONObject jsonObject) {
        return TextUtils.equals(jsonObject.optString("data", ""), "error");
    }
}
