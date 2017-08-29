package com.madmensoftware.com.ui.main;

import com.madmensoftware.com.ui.base.MvpView;

public interface MainMvpView extends MvpView {
//
//    void showBars(List<Bar> bars);
//
//    void showProgress(boolean show);
//
//    void showError(Throwable error);

    void showBarListFragment();

    void showBarDetailFragment(String barName);

    void showLoginFragment();

    void showRegistrationFragment();

    void showNavigationFragment();

    void showPassesFragment();

    void showEventsFragment();

    void showFriendsFragment();

    void showSettingsFragment();

    void showToolbar();

    void hideToolbar();

}
