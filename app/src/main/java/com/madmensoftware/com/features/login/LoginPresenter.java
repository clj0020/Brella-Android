package com.madmensoftware.com.features.login;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.features.base.BasePresenter;
import com.madmensoftware.com.injection.ConfigPersistent;
import com.madmensoftware.com.util.rx.scheduler.SchedulerUtils;

import javax.inject.Inject;

/**
 * Created by clj00 on 8/18/2017.
 */
@ConfigPersistent
public class LoginPresenter extends BasePresenter<LoginMvpView> {

    private final DataManager dataManager;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(LoginMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void loginSubmitted(LoginMvpView mvpView) {
        mvpView.navigateToHomeScreen();
    }

}

