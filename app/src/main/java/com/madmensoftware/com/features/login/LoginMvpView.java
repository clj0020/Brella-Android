package com.madmensoftware.com.features.login;

import com.madmensoftware.com.features.base.MvpView;

import java.util.List;

/**
 * Created by clj00 on 8/18/2017.
 */

public interface LoginMvpView extends MvpView {

    void showProgress(boolean show);

    void showError(Throwable error);

    void navigateToHomeScreen();
}
