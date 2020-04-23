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
import com.tohsoft.airquality.data.Key;
import com.tohsoft.airquality.data.models.breezometer.Data;
import com.tohsoft.airquality.data.models.breezometer.Index;
import com.tohsoft.airquality.data.models.breezometer.Pollutant;
import com.tohsoft.airquality.data.models.breezometer.Weather;
import com.tohsoft.airquality.ui.base.BaseFragment;
import com.tohsoft.airquality.ui.base.BasePresenter;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class BreezometerFragment extends BaseFragment {
    @BindView(R.id.tv_air_moderate)
    TextView tvModerate;
    @BindView(R.id.tv_air_index)
    TextView tvAirIndex;
    @BindView(R.id.tv_air_des)
    TextView tvAirDes;
    @BindView(R.id.rl_air_quality)
    View rlAirQualityColor;

    @BindView(R.id.llpm25)
    LinearLayout llpm25;
    @BindView(R.id.llco)
    LinearLayout llco;
    @BindView(R.id.llpm10)
    LinearLayout llpm10;
    @BindView(R.id.llo3)
    LinearLayout llo3;
    @BindView(R.id.llso2)
    LinearLayout llso2;
    @BindView(R.id.llno2)
    LinearLayout llno2;

    @BindView(R.id.tv_pm25_value)
    TextView tvPm25;
    @BindView(R.id.tvco_value)
    TextView tvCo;
    @BindView(R.id.tv_pm10_value)
    TextView tvPm10;
    @BindView(R.id.tv_o3_value)
    TextView tvO3;
    @BindView(R.id.tv_so2_value)
    TextView tvSo2;
    @BindView(R.id.tv_no2_value)
    TextView tvNo2;

    @BindView(R.id.ll_health_recommend)
    LinearLayout llHealthRecommendation;
    @BindView(R.id.ll_history)
    LinearLayout ll_history;
    @BindView(R.id.ll_forecast)
    LinearLayout ll_forecast;

    double hnLat = 21.0278;
    double hnLon = 105.8342;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_breezometer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        getData();
        getHourly();
        getForecast();
        getWeather(view);
    }


    private void getData() {

        ApplicationModules.getInstant().getDataManager().getBreezometerAirQuality(hnLat, hnLon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataResponse -> {
                    Data data = dataResponse.getData();

                    // moderate
                    if (data == null) return;

                    for (String key : data.getIndexes().keySet()) {
                        Index index = data.getIndexes().get(key);
                        if (key.equals("baqi")) continue;
                        if (index != null) {
                            tvModerate.setText(index.getAqi() + "");
                            tvAirIndex.setText(index.getDisplay_name() + "");
                            tvAirDes.setText(index.getCategory() + "");
                            rlAirQualityColor.setBackgroundColor(Color.parseColor(index.getColor()));
                            break;
                        }

                    }
                    // detail
                    Pollutant pollutant = data.getPollutants();
                    if (pollutant != null) {
                        Pollutant.CoBean coBean = pollutant.getCo();
                        if (coBean != null) {
                            tvCo.setText(coBean.getConcentration().getValue() + "");
                        }
                        Pollutant.No2Bean no2Bean = pollutant.getNo2();
                        if (no2Bean != null) {
                            tvNo2.setText(no2Bean.getConcentration().getValue() + "");
                        }
                        Pollutant.O3Bean o3 = pollutant.getO3();
                        if (o3 != null) {
                            tvO3.setText(o3.getConcentration().getValue() + "");
                        }
                        Pollutant.Pm10Bean pm10 = pollutant.getPm10();
                        if (pm10 != null) {
                            tvPm10.setText(pm10.getConcentration().getValue() + "");
                        }
                        Pollutant.Pm25Bean pm25 = pollutant.getPm25();
                        if (pm25 != null) {
                            tvPm25.setText(pm25.getConcentration().getValue() + "");
                        }
                        Pollutant.So2Bean so2 = pollutant.getSo2();
                        if (so2 != null) {
                            tvSo2.setText(so2.getConcentration().getValue() + "");
                        }
                    }

                    //health recommendation
                    HashMap<String, String> healthRecommend = data.getHealth_recommendations();
                    if (healthRecommend != null) {
                        llHealthRecommendation.removeAllViews();
                        for (String key : healthRecommend.keySet()) {
                            String value = "-" + healthRecommend.get(key);
                            TextView tvValue = new TextView(getContext());
                            tvValue.setText(value);
                            llHealthRecommendation.addView(tvValue);
                        }
                    }

                }, throwable -> errorMaybeChangeKey(throwable));
    }

    private void getHourly() {
        ApplicationModules.getInstant().getDataManager().getBreezometerHourly(hnLat, hnLon, 24)
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
                                tvTime.setText(TimeUtils.millis2String(TimeUtils.string2Millis(data.getDatetime(), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSS'Z'")), new SimpleDateFormat("hh:mm dd/MM/yyyy")) + "");
                                backGround.setBackgroundColor(Color.parseColor(index.getColor()));
                            }

                            ll_history.addView(itemHourView);
                        }
                    }

                }, throwable -> errorMaybeChangeKey(throwable));
    }

    private void getForecast() {
        ApplicationModules.getInstant().getDataManager().getBreezometerForecast(hnLat, hnLon, 24)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataResponse -> {
                    List<Data> dataList = dataResponse.getData();

                    // moderate
                    if (dataList == null) return;
                    ll_forecast.removeAllViews();
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
                                tvTime.setText(TimeUtils.millis2String(TimeUtils.string2Millis(data.getDatetime(), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSS'Z'")), new SimpleDateFormat("hh:mm dd/MM/yyyy")) + "");
                                backGround.setBackgroundColor(Color.parseColor(index.getColor()));
                            }

                            ll_forecast.addView(itemHourView);
                        }
                    }

                }, throwable -> errorMaybeChangeKey(throwable));
    }

    private void getWeather(View view) {
        ApplicationModules.getInstant().getDataManager().getBreezometerWeather(hnLat, hnLon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataResponse -> {
                    Weather weather = dataResponse.getData();

                    if (weather == null) return;

                    TextView tvTime = view.findViewById(R.id.tv_time);
                    if (tvTime != null) {
                        tvTime.setText(weather.getDatetime() + "");
                    }

                    ((TextView) (view.findViewById(R.id.tv_tp))).setText(weather.getTemperature().getValue() + "\n°C");
                    ((TextView) (view.findViewById(R.id.tv_pr))).setText(weather.getPressure().getValue() + "\n(" + weather.getPressure().getUnits() + ")");
                    ((TextView) (view.findViewById(R.id.tv_hu))).setText(weather.getRelative_humidity() + "\n%");
                    ((TextView) (view.findViewById(R.id.tv_ws))).setText(weather.getWind().getSpeed().getValue() + "\n(" + weather.getWind().getSpeed().getUnits() + ")");
                    ((TextView) (view.findViewById(R.id.tv_wd))).setText(weather.getWind().getDirection() + "°");
                    ((TextView) (view.findViewById(R.id.tv_precipitation))).setText(weather.getPrecipitation().getPrecipitation_probability() + "\n%");
                    ((TextView) (view.findViewById(R.id.tv_visibility))).setText(weather.getVisibility().getValue() + "\n(" + weather.getVisibility().getUnits() + ")");
                    ((TextView) (view.findViewById(R.id.tv_dew_point))).setText(weather.getDew_point().getValue() + "\n°C");
                    ((TextView) (view.findViewById(R.id.tv_cloud_cover))).setText(weather.getCloud_cover() + "\n%");

                }, throwable -> {
                    errorMaybeChangeKey(throwable);
                });
    }

    private void errorMaybeChangeKey(Throwable throwable) {
        LogUtils.e(throwable);
        Key.getKeyBreezometer(true);
    }

    @Override
    protected BasePresenter onRegisterPresenter() {
        return null;
    }
}
