package com.madmensoftware.com.ui.passes;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.injection.ConfigPersistent;
import com.madmensoftware.com.ui.base.BasePresenter;
import com.madmensoftware.com.util.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by clj00 on 8/24/2017.
 */

@ConfigPersistent
public class PassesPresenter extends BasePresenter<PassesMvpView> {

    @Inject
    public PassesPresenter(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void attachView(PassesMvpView mvpView) {
        super.attachView(mvpView);
    }



}