package com.tohsoft.airquality.ui.demo.pay;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.tohsoft.airquality.R;
import com.tohsoft.airquality.data.ApplicationModules;
import com.tohsoft.airquality.data.models.ambee.Weather;
import com.tohsoft.airquality.data.models.breezometer.Data;
import com.tohsoft.airquality.data.models.breezometer.Index;
import com.tohsoft.airquality.ui.base.BaseFragment;
import com.tohsoft.airquality.ui.base.BasePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class AmbeeFragment extends BaseFragment {
    @Nullable
    @BindView(R.id.tv_air_moderate)
    TextView tvModerate;
    @Nullable
    @BindView(R.id.tv_air_index)
    TextView tvAirIndex;
    @Nullable
    @BindView(R.id.tv_air_des)
    TextView tvAirDes;
    @Nullable
    @BindView(R.id.rl_air_quality)
    View rlAirQualityColor;


    @Nullable
    @BindView(R.id.tv_p2)
    TextView tvPm25;
    @Nullable
    @BindView(R.id.tv_co)
    TextView tvCo;
    @Nullable
    @BindView(R.id.tv_p1)
    TextView tvPm10;
    @Nullable
    @BindView(R.id.tv_o3)
    TextView tvO3;
    @Nullable
    @BindView(R.id.tv_s2)
    TextView tvSo2;
    @Nullable
    @BindView(R.id.tv_no2)
    TextView tvNo2;
    @Nullable
    @BindView(R.id.tv_time)
    TextView tvTime;

    @Nullable
    @BindView(R.id.ll_history)
    LinearLayout ll_history;

    double usLat = 47.7511;
    double usLon = 120.7401;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ambee, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        getData();
        getWeather(view);
        getHourly();
    }

    private void getData() {

        ApplicationModules.getInstant().getDataManager().getAmbeeAirQuality(usLat, usLon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataResponse -> {
                    List<com.tohsoft.airquality.data.models.ambee.Data.StationsBean> data = dataResponse.getStations();
                    if (data != null && data.size() > 0) {
                        com.tohsoft.airquality.data.models.ambee.Data.StationsBean stationsBean = data.get(0);

                        // moderate
                        if (data == null) return;


                        tvModerate.setText(stationsBean.getAQI() + "");
                        tvAirDes.setText(stationsBean.getAqiInfo() + "");
                        tvAirIndex.setText("Washington DC, US");
                        tvTime.setText(stationsBean.getUpdatedAt() + "");

                        // detail

                        tvCo.setText(stationsBean.getCO() + "");

                        tvNo2.setText(stationsBean.getNO2() + "");

                        tvO3.setText(stationsBean.getOZONE() + "");

                        tvPm10.setText(stationsBean.getPM10() + "");

                        tvPm25.setText(stationsBean.getPM25() + "");

                        tvSo2.setText(stationsBean.getSO2() + "");


                    }

                }, throwable -> LogUtils.e(throwable));
    }

    private void getHourly() {
        ApplicationModules.getInstant().getDataManager().getBreezometerHourly(usLat, usLon, 24)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataResponse -> {
                    List<Data> dataList = dataResponse.getData();

                    // moderate
                    if (dataList == null) return;
                    ll_history.removeAllViews();
                    for (Data data : dataList) {
                        for (String key : data.getIndexes().keySet()) {
                            Index index = data.getIndexes().get(key);
                            if (key.equals("baqi")) continue;

                            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View itemHourView = layoutInflater.inflate(R.layout.item_hourly, null, false);

                            TextView tvApi = itemHourView.findViewById(R.id.tv_aqi);
                            TextView tvTime = itemHourView.findViewById(R.id.tv_air_time);
                            View backGround = itemHourView.findViewById(R.id.rl_air_quality);


                            if (index != null) {
                                tvApi.setText(index.getAqi() + "");
                                tvTime.setText(data.getDatetime() + "");
                                backGround.setBackgroundColor(Color.parseColor(index.getColor()));
                            }

                            ll_history.addView(itemHourView);
                        }
                    }

                }, throwable -> LogUtils.e(throwable));
    }

    private void getWeather(View view) {
        ApplicationModules.getInstant().getDataManager().getAmbeeWeather(usLat, usLon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataResponse -> {
                    Weather weather = dataResponse.getData();

                    if (weather == null) return;

                    TextView tvTime = view.findViewById(R.id.tv_time);
                    if (tvTime != null) {
                        tvTime.setText(TimeUtils.millis2String(weather.getTime() * 1000) + "");
                    }

                    ((TextView) (view.findViewById(R.id.tv_sumary))).setText("Sumarry: " + weather.getSummary() + "");
                    ((TextView) (view.findViewById(R.id.tv_tp))).setText(weather.getTemperature() + "");
                    ((TextView) (view.findViewById(R.id.tv_pr))).setText(weather.getPressure() + "");
                    ((TextView) (view.findViewById(R.id.tv_hu))).setText(weather.getHumidity() + "");
                    ((TextView) (view.findViewById(R.id.tv_ws))).setText(weather.getWindSpeed() + "");
                    ((TextView) (view.findViewById(R.id.tv_wd))).setText(weather.getWindGust() + "");
                    ((TextView) (view.findViewById(R.id.tv_precipitation))).setText(weather.getPrecipProbability() + "");
                    ((TextView) (view.findViewById(R.id.tv_visibility))).setText(weather.getVisibility() + "");
                    ((TextView) (view.findViewById(R.id.tv_dew_point))).setText(weather.getDewPoint() + "");
                    ((TextView) (view.findViewById(R.id.tv_cloud_cover))).setText(weather.getCloudCover() + "");
                    ((TextView) (view.findViewById(R.id.tv_uv))).setText(weather.getUvIndex() + "");

                }, throwable -> LogUtils.e(throwable));
    }

    @Override
    protected BasePresenter onRegisterPresenter() {
        return null;
    }
}
