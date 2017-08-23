package com.madmensoftware.com.features.main;

import android.util.Log;

import javax.inject.Inject;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.data.model.response.Bar;
import com.madmensoftware.com.features.base.BasePresenter;
import com.madmensoftware.com.injection.ConfigPersistent;
import com.madmensoftware.com.util.rx.scheduler.SchedulerUtils;

import java.util.List;

import retrofit2.Call;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager dataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
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
                            getView().showProgress(false);
                            getView().showBars((List<Bar>) bars);
                        },
                        throwable -> {
                            getView().showProgress(false);
                            getView().showError(throwable);
                        });
    }
}
