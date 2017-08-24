package com.madmensoftware.com.features.passes;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.features.base.BasePresenter;
import com.madmensoftware.com.features.navigation.NavigationMvpView;

import javax.inject.Inject;

/**
 * Created by clj00 on 8/24/2017.
 */

public class PassesPresenter extends BasePresenter<PassesMvpView> {

    private final DataManager dataManager;

    @Inject
    public PassesPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(PassesMvpView mvpView) {
        super.attachView(mvpView);
    }



}