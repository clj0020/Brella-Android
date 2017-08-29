package com.madmensoftware.com.ui.events;

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
public class EventsPresenter extends BasePresenter<EventsMvpView> {

    @Inject
    public EventsPresenter(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    @Override
    public void attachView(EventsMvpView mvpView) {
        super.attachView(mvpView);
    }

}
