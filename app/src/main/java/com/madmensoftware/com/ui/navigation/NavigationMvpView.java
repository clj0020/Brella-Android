package com.madmensoftware.com.ui.navigation;

import com.madmensoftware.com.ui.base.MvpView;

/**
 * Created by clj00 on 8/23/2017.
 */

public interface NavigationMvpView extends MvpView {


    void navigateToHome();

    void navigateToPasses();

    void navigateToEvents();

    void navigateToBars();

    void navigateToFriends();

    void navigateToSettings();
}
