package com.madmensoftware.com.features.main;

import android.util.Log;

import javax.inject.Inject;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.data.model.response.Bar;
import com.madmensoftware.com.features.base.BasePresenter;
import com.madmensoftware.com.injection.ConfigPersistent;
import com.madmensoftware.com.util.rx.scheduler.SchedulerUtils;

import java.util.List;

import retrofit2.Call;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager dataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void showBarListFragment(MainMvpView mainMvpView) {
        mainMvpView.showBarListFragment();
    }

    public void showToolbar(MainMvpView mainMvpView) {
        mainMvpView.showToolbar();
    }

    public void hideToolbar(MainMvpView mainMvpView) {
        mainMvpView.hideToolbar();
    }


    public void showBarDetailFragment(MainMvpView mainMvpView, String barName) {
        mainMvpView.showBarDetailFragment(barName);
    }


}
