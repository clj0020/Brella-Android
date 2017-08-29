package com.madmensoftware.com.ui.login;

import com.madmensoftware.com.ui.base.MvpView;

/**
 * Created by clj00 on 8/18/2017.
 */

public interface LoginMvpView extends MvpView {

    void showProgress(boolean show);

    void showError(Throwable error);

    void showBarListFragment();

    void goToRegistration();
}
