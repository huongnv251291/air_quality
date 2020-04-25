package com.tohsoft.airquality.ui.demo.free.ranking;

import android.content.Context;
import android.util.Log;

import com.tohsoft.airquality.data.ApplicationModules;
import com.tohsoft.airquality.data.models.aqicn.RankingCountry;
import com.tohsoft.airquality.data.network.DataManager;
import com.tohsoft.airquality.ui.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DemoDataRankingPresenter<V extends DemoDataRankingMvpView> extends BasePresenter<V> implements DemoDataRankingMvpPresenter<V> {
    DataManager dataManager;

    public DemoDataRankingPresenter(Context context) {
        super(context);
        dataManager = ApplicationModules.getInstant().getDataManager();
    }

    @Override
    public void loadData() {
        mCompositeDisposable.add(dataManager.getListTopRanking().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<RankingCountry>() {
            @Override
            public void accept(RankingCountry rankingCountry) throws Exception {
                Log.e("RankingCountry", rankingCountry.toString());
                getMvpView().updateCurrent(rankingCountry);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                getMvpView().requestFail();
                throwable.printStackTrace();
            }
        }));
    }
}
