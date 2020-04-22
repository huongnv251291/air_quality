package com.tohsoft.airquality.ui.demo.ranking;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tohsoft.airquality.R;
import com.tohsoft.airquality.data.models.RankingCountry;
import com.tohsoft.airquality.ui.base.BaseFragmentSetup;
import com.tohsoft.airquality.ui.base.BasePresenter;
import com.tohsoft.airquality.ui.demo.ranking.adapter.AdapterRanking;

import butterknife.BindView;

public class FragmentDemoDataRanking extends BaseFragmentSetup<DemoDataRankingMvpPresenter> implements DemoDataRankingMvpView, View.OnClickListener {
    @BindView(R.id.list_ranking)
    RecyclerView recyclerView;
    private AdapterRanking adapterRanking;

    @Override
    protected BasePresenter onRegisterPresenter() {
        return new DemoDataRankingPresenter(mContext);
    }

    @Override
    public Integer getIdBg() {
        return null;
    }

    @Override
    public int getIdLayout() {
        return R.layout.frm_demo_ranking;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mPresenter.loadData();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        adapterRanking = new AdapterRanking();
        adapterRanking.setOnClickListener(this);
        recyclerView.setAdapter(adapterRanking);
    }


    @Override
    public void updateCurrent(RankingCountry rankingCountry) {
        adapterRanking.setData(rankingCountry.getCities());
    }

    @Override
    public void requestFail() {

    }

    @Override
    public void onClick(View v) {

    }
}
