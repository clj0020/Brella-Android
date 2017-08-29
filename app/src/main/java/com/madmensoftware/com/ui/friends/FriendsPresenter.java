package com.madmensoftware.com.ui.friends;

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
public class FriendsPresenter extends BasePresenter<FriendsMvpView> {

    @Inject
    public FriendsPresenter(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    @Override
    public void attachView(FriendsMvpView mvpView) {
        super.attachView(mvpView);
    }



}
