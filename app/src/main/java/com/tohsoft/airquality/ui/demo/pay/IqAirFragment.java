package com.tohsoft.airquality.ui.demo.pay;

import android.content.Context;
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
import com.tohsoft.airquality.data.models.iqair.Data;
import com.tohsoft.airquality.data.models.iqair.Ranking;
import com.tohsoft.airquality.ui.base.BaseFragment;
import com.tohsoft.airquality.ui.base.BasePresenter;
import com.utility.UtilsLib;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class IqAirFragment extends BaseFragment {
    @BindView(R.id.tv_air_moderate)
    TextView tvModerate;
    @BindView(R.id.tv_air_address)
    TextView tvAirDress;
    @BindView(R.id.ll_forecast)
    LinearLayout llForecast;
    @BindView(R.id.ll_history)
    LinearLayout llHistory;
    @BindView(R.id.ll_ranking)
    LinearLayout llRanking;


    String city = "Cau Giay";
    String state = "Hanoi";
    String country = "Vietnam";
    LayoutInflater layoutInflater;
    View screen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_iqair, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        screen = view;
        ButterKnife.bind(this, view);
        layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        getData();
        getRanking();
    }


    private void getData() {
        ApplicationModules.getInstant().getDataManager().getIQAirQuality(city, state, country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataResponse -> {
                    Data data = dataResponse.getData();
                    if (data != null) {
                        Data.Current.Pollution pollution = data.getCurrent().getPollution();
                        tvModerate.setText(pollution.getAqius() + "");
                        tvAirDress.setText(city + "," + state + "," + country);
                        bindViewPollution(data.getCurrent().getPollution(), getView());
                        bindViewForecast(data.getForecasts());
                        bindViewHourly(data.getHistory().getPollution());
                    }
                }, throwable -> {
                    LogUtils.e(throwable);
                });
    }

    private void bindViewHourly(List<Data.Current.Pollution> pollution) {
        if (pollution != null && pollution.size() > 0) {
            llHistory.removeAllViews();
            for (Data.Current.Pollution item : pollution) {
                View view = layoutInflater.inflate(R.layout.item_hourly, null, false);

                TextView tvApi = view.findViewById(R.id.tv_aqi);
                TextView tvTime = view.findViewById(R.id.tv_air_time);
                View backGround = view.findViewById(R.id.rl_air_quality);


                tvApi.setText(item.getAqius() + "");
                tvTime.setText(getTime(item.getTs()));
//                backGround.setBackgroundColor(Color.parseColor(index.getColor()));

                llHistory.addView(view);
            }
        }
    }


    private void getRanking() {
        ApplicationModules.getInstant().getDataManager().getIQAirRanking()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataResponse -> {
                    List<Ranking> datas = dataResponse.getData();
                    llRanking.removeAllViews();
                    for (Ranking ranking : datas) {
                        if (ranking != null) {
                            View view = layoutInflater.inflate(R.layout.item_ranking_pay, null, false);
                            ((TextView) view.findViewById(R.id.tv_ranking_number)).setText(ranking.getRanking().getCurrent_aqi() + "");
                            ((TextView) view.findViewById(R.id.tv_ranking_country)).setText(ranking.getState() + "," + ranking.getCity() + "," + ranking.getCountry());
                            ((TextView) view.findViewById(R.id.tv_ranking_time)).setText(ranking.getRanking().getUpdated() + "");
                            llRanking.addView(view);
                        }
                    }
                }, throwable -> {
                    LogUtils.e(throwable);
                });
    }

    private void bindViewPollution(Data.Current.Pollution pollution, View viewParent) {
        TextView tvTime = screen.findViewById(R.id.tv_time);
        TextView tvS2 = screen.findViewById(R.id.tv_s2);
        TextView tvNo2 = screen.findViewById(R.id.tv_no2);
        TextView tvO3 = screen.findViewById(R.id.tv_o3);
        TextView tvCO = screen.findViewById(R.id.tv_co);
        TextView tvP2 = screen.findViewById(R.id.tv_p2);
        TextView tvP1 = screen.findViewById(R.id.tv_p1);
        if (tvTime != null) {
            tvTime.setText(pollution.getTs() + "");
        }
        if (tvS2 != null && pollution.getS2() != null) {
            tvS2.setText(pollution.getS2().getAqius() + "(ppb)");
        }
        if (tvNo2 != null && pollution.getN2() != null) {
            tvNo2.setText(pollution.getN2().getAqius() + "(ppb)");
        }
        if (tvO3 != null && pollution.getO3() != null) {
            tvO3.setText(pollution.getO3().getAqius() + "(ppb)");
        }
        if (tvCO != null && pollution.getCo() != null) {
            tvCO.setText(pollution.getCo().getAqius() + "(ppm)");
        }
        if (tvP2 != null && pollution.getP2() != null) {
            tvP2.setText(pollution.getP2().getAqius() + "(ugm3)");
        }
        if (tvP1 != null && pollution.getP1() != null) {
            tvP1.setText(pollution.getP1().getAqius() + "(ugm3)");
        }
    }

    private void bindViewForecast(List<Data.Current.Weather> forecasts) {
        if (forecasts != null && forecasts.size() > 0) {
            llForecast.removeAllViews();
            for (Data.Current.Weather item : forecasts) {
                View view = layoutInflater.inflate(R.layout.item_hourly, null, false);

                TextView tvApi = view.findViewById(R.id.tv_aqi);
                TextView tvTime = view.findViewById(R.id.tv_air_time);
                View backGround = view.findViewById(R.id.rl_air_quality);


                tvApi.setText(item.getAqius() + "");
                tvTime.setText(getTime(item.getTs()));

                llForecast.addView(view);
            }
        }
    }

    private void bindForecast(Data.Current.Weather weather, View view) {
        TextView tvScore = view.findViewById(R.id.tv_score);
        TextView tvTime = view.findViewById(R.id.tv_time);
        if (tvScore != null && tvTime != null) {
            tvScore.setText(+weather.getAqius() + "");
            tvTime.setText(weather.getTs());
        }

//
//        ((TextView) (view.findViewById(R.id.tv_tp))).setText(weather.getTp() + "°C");
//        ((TextView) (view.findViewById(R.id.tv_pr))).setText(weather.getPr() + "(hPa)");
//        ((TextView) (view.findViewById(R.id.tv_hu))).setText(weather.getHu() + "%");
//        ((TextView) (view.findViewById(R.id.tv_ws))).setText(weather.getWs() + "(m/s)");
//        ((TextView) (view.findViewById(R.id.tv_wd))).setText(weather.getWd() + "°");
//        Glide.with(getContext()).load("https://www.airvisual.com/images/" + weather.getIc() + ".png").into((ImageView) view.findViewById(R.id.iv_icon));
    }

    private String getTime(String time) {
        long millis = TimeUtils.string2Millis(time, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        return UtilsLib.getDateTimeByTimezone(millis, TimeZone.getDefault().getID(), "hh:mm dd/MM/yyyy");
    }
//        Date date = new Date(millis);
//        SimpleDateFormat myDate = new SimpleDateFormat("hh:mm dd/MM/yyyy");
//        myDate.setTimeZone(TimeZone.getDefault());
//        return TimeUtils.millis2String(millis, myDate);
//    }


    @Override
    protected BasePresenter onRegisterPresenter() {
        return null;
    }
}
