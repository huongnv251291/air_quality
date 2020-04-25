package com.tohsoft.airquality.ui.demo.free.current;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.tohsoft.airquality.R;
import com.tohsoft.airquality.data.models.aqicn.AqiOfCity;
import com.tohsoft.airquality.ui.base.BaseFragmentSetup;
import com.tohsoft.airquality.ui.base.BasePresenter;

import butterknife.BindView;

public class FragmentDemoDataCurrent extends BaseFragmentSetup<DemoDataCurrentMvpPresenter> implements DemoDataCurrentMvpView {
    @BindView(R.id.textdemo)
    AppCompatTextView textView;

    @Override
    protected BasePresenter onRegisterPresenter() {
        return new DemoDataCurrentPresenter(mContext);
    }

    @Override
    public Integer getIdBg() {
        return null;
    }

    @Override
    public int getIdLayout() {
        return R.layout.frm_demo_current;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mPresenter.loadData();
    }

    @Override
    public void updateCurrent(AqiOfCity jsonObject) {
        textView.setText(jsonObject.toString());
    }

    @Override
    public void requestFail() {

    }
}
