package com.tohsoft.airquality.ui.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.tohsoft.airquality.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public abstract class BaseFragmentSetup<V extends MvpPresenter> extends BaseFragment<V> {
    protected View rootView;
    protected Unbinder unbinder;
    private Disposable disposable;
    protected boolean disableSettingBg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(getIdLayout(), container, false);
        rootView.setOnTouchListener((v, event) -> true);
        if (!disableSettingBg) {
            rootView.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            trySetBg();
        }
        bindView(rootView);
        return rootView;
    }

    public void bindView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    private void trySetBg() {
        rootView.post(() -> disposable = Observable.create(
                (ObservableOnSubscribe<Drawable>) emitter -> {
                    if (getIdBg() != null && getIdBg() != 0) {
                        Glide.with(rootView.getContext()).load(getIdBg())
                                .thumbnail(0.5f)
                                .into(new CustomTarget<Drawable>() {
                                    @Override
                                    public void onResourceReady(@NonNull Drawable resource,
                                                                @Nullable Transition<? super Drawable> transition) {
                                        if (isAdded()) {
                                            emitter.onNext(resource);
                                        }
                                        emitter.onComplete();
                                    }

                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) {

                                    }
                                });
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(
                bitmap -> rootView.setBackground(bitmap)));
    }

    public abstract Integer getIdBg();

    public abstract int getIdLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
