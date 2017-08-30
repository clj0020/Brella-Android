package com.madmensoftware.com.ui.registration;

import android.support.design.widget.TextInputEditText;
import android.widget.EditText;

import com.madmensoftware.com.ui.base.MvpView;

/**
 * Created by clj00 on 8/25/2017.
 */

public interface RegistrationMvpView extends MvpView {

    void showProgress(boolean show);

    void showError(Throwable error);

    void showBarListFragment();

    void goToLogin();

    void hideKeyboard();

    boolean validInputs();

}
