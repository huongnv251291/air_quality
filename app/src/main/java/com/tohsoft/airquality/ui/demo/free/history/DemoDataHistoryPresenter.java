package com.tohsoft.airquality.ui.demo.free.history;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.tohsoft.airquality.data.ApplicationModules;
import com.tohsoft.airquality.data.models.aqicn.GraphModel;
import com.tohsoft.airquality.data.network.DataManager;
import com.tohsoft.airquality.ui.base.BasePresenter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class DemoDataHistoryPresenter<V extends DemoDataHistoryMvpView> extends BasePresenter<V> implements DemoDataHistoryMvpPresenter<V> {
    DataManager dataManager;

    public DemoDataHistoryPresenter(Context context) {
        super(context);
        dataManager = ApplicationModules.getInstant().getDataManager();
    }

    @Override
    public void loadData() {
        if (ApplicationModules.getInstant().getStaticData() != null) {
            updateHistoric(ApplicationModules.getInstant().getStaticData());
            return;
        }
        mCompositeDisposable.add(dataManager.getForecastData(mContext, "_C8tMLclLzNX3SMzLz9QPDXbNTUosLq4EAA", "Vietnam%2FHanoi%2FUSEmbassy").observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                Log.e("getForecastData", responseBody.toString());
                JSONObject jsonObject = new JSONObject(responseBody.string());
                if (requestFail(jsonObject)) {
                    getMvpView().requestFail();
                } else {
                    updateHistoric(jsonObject);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();

            }
        }));
    }

    private void updateHistoric(JSONObject staticData) {
        JSONArray array = staticData.optJSONArray("historic");
        if (array == null) {
            if (ApplicationModules.getInstant().getStaticData() == null) {
                getMvpView().requestFail();
                return;
            }
            ApplicationModules.getInstant().updateStaticData(null);
            loadData();
            return;
        }
        List<GraphModel> graphModels = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = array.optJSONObject(i);
            GraphModel graph = GraphModel.get(staticData, jsonObject);
            if (graph == null) {
                continue;
            }
            graphModels.add(graph);
        }
        getMvpView().updateHistoryData(graphModels);
        ApplicationModules.getInstant().updateStaticData(staticData);

    }

    private boolean requestFail(JSONObject jsonObject) {
        return TextUtils.equals(jsonObject.optString("data", ""), "error");
    }
}

