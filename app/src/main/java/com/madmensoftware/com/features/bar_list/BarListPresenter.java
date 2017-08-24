package com.madmensoftware.com.features.bar_list;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.data.model.response.Bar;
import com.madmensoftware.com.features.base.BasePresenter;
import com.madmensoftware.com.util.rx.scheduler.SchedulerUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by clj00 on 8/23/2017.
 */

public class BarListPresenter extends BasePresenter<BarListMvpView> {

    private final DataManager dataManager;

    @Inject
    public BarListPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(BarListMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void getBars(int limit) {
        checkViewAttached();
        getView().showProgress(true);

        dataManager
                .getBarList(limit)
                .compose(SchedulerUtils.ioToMain())
                .subscribe(
                        bars -> {
                            if (isViewAttached()) {
                                getView().showProgress(false);
                                getView().showBars((List<Bar>) bars);
                            }
                        },
                        throwable -> {
                            if (isViewAttached()) {
                                getView().showProgress(false);
                                getView().showError(throwable);
                            }
                        });
    }

}