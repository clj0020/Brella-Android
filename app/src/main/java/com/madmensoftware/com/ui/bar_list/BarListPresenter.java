package com.madmensoftware.com.ui.bar_list;

import android.util.Log;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.data.model.response.Bar;
import com.madmensoftware.com.injection.ConfigPersistent;
import com.madmensoftware.com.ui.base.BasePresenter;
import com.madmensoftware.com.util.SchedulerProvider;
import com.madmensoftware.com.util.rx.scheduler.SchedulerUtils;
import com.parse.ParseUser;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by clj00 on 8/23/2017.
 */

@ConfigPersistent
public class BarListPresenter extends BasePresenter<BarListMvpView> {

    public CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public BarListPresenter(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void attachView(BarListMvpView mvpView) {
        super.attachView(mvpView);

        if(compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    @Override
    public void detachView() {
        super.detachView();

        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    public void getBars(int limit) {
        checkViewAttached();
        getView().showProgress(true);

        compositeDisposable.add(getDataManager()
                .getBarList(limit)
                .subscribeWith(new DisposableObserver<List<Bar>>() {
                    @Override
                    public void onNext(List<Bar> bars) {

                    if (!isViewAttached()) {
                        Timber.e("View is not attached");
                    }

                    getView().showProgress(false);
                    getView().showBars(bars);
                }

                @Override
                public void onError(Throwable e) {
                    Timber.e(e);

                if (!isViewAttached()) {
                    Timber.e("View is not attached");
                }
                getView().showProgress(false);
                getView().showError(e);
            }

            @Override
            public void onComplete() {

            }
        }));

    }

    public void unsubscribe() {
        compositeDisposable.clear();
    }

}