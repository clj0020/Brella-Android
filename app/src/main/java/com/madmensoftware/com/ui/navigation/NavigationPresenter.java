package com.madmensoftware.com.ui.navigation;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.injection.ConfigPersistent;
import com.madmensoftware.com.ui.base.BasePresenter;
import com.madmensoftware.com.util.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by clj00 on 8/23/2017.
 */

@ConfigPersistent
public class NavigationPresenter extends BasePresenter<NavigationMvpView> {

    @Inject
    public NavigationPresenter(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void attachView(NavigationMvpView mvpView) {
        super.attachView(mvpView);
    }


    public void navigateToHome(NavigationMvpView mvpView) {
        mvpView.navigateToHome();
    }

    public void navigateToPasses(NavigationMvpView mvpView) {
        mvpView.navigateToPasses();
    }

    public void navigateToEvents(NavigationMvpView mvpView) {
        mvpView.navigateToEvents();
    }

    public void navigateToBars(NavigationMvpView mvpView) {
        mvpView.navigateToBars();
    }


    public void navigateToFriends(NavigationMvpView mvpView) {
        mvpView.navigateToFriends();
    }

    public void navigateToSettings(NavigationMvpView mvpView) {
        mvpView.navigateToSettings();
    }
}
