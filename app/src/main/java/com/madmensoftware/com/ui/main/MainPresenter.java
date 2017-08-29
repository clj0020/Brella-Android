package com.madmensoftware.com.ui.main;

import javax.inject.Inject;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.ui.base.BasePresenter;
import com.madmensoftware.com.injection.ConfigPersistent;
import com.madmensoftware.com.util.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    @Inject
    public MainPresenter(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
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

    public void showLoginFragment(MainMvpView mainMvpView) {
        mainMvpView.showLoginFragment();
    }

    public void showRegistrationFragment(MainMvpView mainMvpView) {
        mainMvpView.showRegistrationFragment();
    }

    public void showBarDetailFragment(MainMvpView mainMvpView, String barName) {
        mainMvpView.showBarDetailFragment(barName);
    }


}
