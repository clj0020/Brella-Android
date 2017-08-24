package com.madmensoftware.com.features.bar_detail;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.features.base.BasePresenter;
import com.madmensoftware.com.injection.ConfigPersistent;
import com.madmensoftware.com.util.rx.scheduler.SchedulerUtils;

import javax.inject.Inject;

/**
 * Created by clj00 on 8/23/2017.
 */

@ConfigPersistent
public class BarDetailPresenter extends BasePresenter<BarDetailMvpView> {

    private final DataManager dataManager;

    @Inject
    public BarDetailPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(BarDetailMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void getBar(String name) {
        checkViewAttached();

        getView().showProgress(true);
        dataManager
                .getBar(name)
                .compose(SchedulerUtils.ioToMain())
                .subscribe(
                        bar -> {
                            // It should be always checked if MvpView (Fragment or Activity) is attached.
                            // Calling showProgress() on a not-attached fragment will throw a NPE
                            // It is possible to ask isAdded() in the fragment, but it's better to ask in the presenter
                            if (isViewAttached()) {
                                getView().showProgress(false);
                                getView().showBar(bar);
                                getView().showQrCode(bar.getObjectId());
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
