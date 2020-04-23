package com.tohsoft.airquality.ui.demo.free;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.FragmentUtils;
import com.tohsoft.airquality.R;
import com.tohsoft.airquality.ui.demo.free.current.FragmentDemoDataCurrent;
import com.tohsoft.airquality.ui.demo.free.forecast.FragmentDemoDataForeCast;
import com.tohsoft.airquality.ui.demo.free.history.FragmentDemoDataHistory;
import com.tohsoft.airquality.ui.demo.free.map.FragmentDemoDataMap;
import com.tohsoft.airquality.ui.demo.free.ranking.FragmentDemoDataRanking;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class ActivityFree extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_free);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_settings, R.id.btn_history, R.id.btn_forecast, R.id.btn_map, R.id.btn_ranking})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_settings:
                FragmentUtils.add(getSupportFragmentManager(), new FragmentDemoDataCurrent(),
                        android.R.id.content, true, R.anim.fade_in, R.anim.fade_out);
                break;
            case R.id.btn_forecast:
                FragmentUtils.add(getSupportFragmentManager(), new FragmentDemoDataForeCast(),
                        android.R.id.content, true, R.anim.fade_in, R.anim.fade_out);
                break;
            case R.id.btn_map:
                FragmentUtils.add(getSupportFragmentManager(), new FragmentDemoDataMap(),
                        android.R.id.content, true, R.anim.fade_in, R.anim.fade_out);
                break;
            case R.id.btn_ranking:
                FragmentUtils.add(getSupportFragmentManager(), new FragmentDemoDataRanking(),
                        android.R.id.content, true, R.anim.fade_in, R.anim.fade_out);
                break;
            case R.id.btn_history:
                FragmentUtils.add(getSupportFragmentManager(), new FragmentDemoDataHistory(),
                        android.R.id.content, true, R.anim.fade_in, R.anim.fade_out);
//                startActivity(new Intent(mContext, HistoryActivity.class));
                break;
        }
    }
}
