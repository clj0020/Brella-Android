package com.madmensoftware.com.features.events;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.features.base.BasePresenter;
import com.madmensoftware.com.features.navigation.NavigationMvpView;

import javax.inject.Inject;

/**
 * Created by clj00 on 8/24/2017.
 */

public class EventsPresenter extends BasePresenter<EventsMvpView> {

    private final DataManager dataManager;

    @Inject
    public EventsPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(EventsMvpView mvpView) {
        super.attachView(mvpView);
    }

}
