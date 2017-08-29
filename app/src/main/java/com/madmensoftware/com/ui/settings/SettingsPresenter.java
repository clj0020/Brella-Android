package com.madmensoftware.com.ui.settings;

import android.util.Log;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.injection.ConfigPersistent;
import com.madmensoftware.com.ui.base.BasePresenter;
import com.madmensoftware.com.util.SchedulerProvider;
import com.madmensoftware.com.util.rx.scheduler.SchedulerUtils;
import com.parse.ParseUser;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by clj00 on 8/24/2017.
 */

@ConfigPersistent
public class SettingsPresenter extends BasePresenter<SettingsMvpView> {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public SettingsPresenter(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    @Override
    public void attachView(SettingsMvpView mvpView) {
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

    public void logOut() {
        checkViewAttached();

        compositeDisposable.add(getDataManager().logout().subscribeWith(new DisposableObserver<Void>() {
            @Override
            public void onNext(Void aVoid) {

            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e);
            }

            @Override
            public void onComplete() {
                if (!isViewAttached()) {
                    Timber.e("View is not attached.");
                    return;
                }

                getView().showLoginFragment();
            }
        }));
    }

    public void unsubscribe() {
        compositeDisposable.clear();
    }

}
