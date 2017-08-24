package com.madmensoftware.com.features.friends;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.features.base.BasePresenter;
import com.madmensoftware.com.features.navigation.NavigationMvpView;

import javax.inject.Inject;

/**
 * Created by clj00 on 8/24/2017.
 */

public class FriendsPresenter extends BasePresenter<FriendsMvpView> {

    private final DataManager dataManager;

    @Inject
    public FriendsPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(FriendsMvpView mvpView) {
        super.attachView(mvpView);
    }



}
