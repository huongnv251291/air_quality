package com.tohsoft.airquality.ui.demo.free.history;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tohsoft.airquality.R;
import com.tohsoft.airquality.data.models.aqicn.GraphModel;
import com.tohsoft.airquality.ui.base.BaseFragmentSetup;
import com.tohsoft.airquality.ui.base.BasePresenter;
import com.tohsoft.airquality.ui.demo.free.history.adapter.AdapterHistory;

import java.util.List;

import butterknife.BindView;

public class FragmentDemoDataHistory extends BaseFragmentSetup<DemoDataHistoryMvpPresenter> implements DemoDataHistoryMvpView {
    @BindView(R.id.rcv_history)
    RecyclerView recyclerView;
    private AdapterHistory adapterHistory;

    @Override
    protected BasePresenter onRegisterPresenter() {
        return new DemoDataHistoryPresenter(mContext);
    }

    @Override
    public Integer getIdBg() {
        return null;
    }

    @Override
    public int getIdLayout() {
        return R.layout.frm_history;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        adapterHistory = new AdapterHistory();
        recyclerView.setAdapter(adapterHistory);
        mPresenter.loadData();
    }

    @Override
    public void requestFail() {

    }

    @Override
    public void updateHistoryData(List<GraphModel> graphModels) {
        adapterHistory.updateListGraphModel(graphModels);
        Log.e("updateHistoryData", graphModels.toString());
    }
}
