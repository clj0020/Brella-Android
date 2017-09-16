package com.madmensoftware.com.ui.add_payment;

import com.madmensoftware.com.ui.base.MvpView;

/**
 * Created by clj00 on 8/30/2017.
 */

public interface AddPaymentMvpView extends MvpView {

    void showSettingsFragment();

    void showProgress(boolean show);

}
