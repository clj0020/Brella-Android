package com.madmensoftware.com.features.navigation;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.features.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by clj00 on 8/23/2017.
 */

public class NavigationPresenter extends BasePresenter<NavigationMvpView> {

    private final DataManager dataManager;

    @Inject
    public NavigationPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
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
