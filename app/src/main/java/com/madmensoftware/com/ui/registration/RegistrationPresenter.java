package com.madmensoftware.com.ui.registration;

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
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import rx.Subscriber;
import timber.log.Timber;

/**
 * Created by clj00 on 8/25/2017.
 */

@ConfigPersistent
public class RegistrationPresenter extends BasePresenter<RegistrationMvpView> {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public RegistrationPresenter(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void attachView(RegistrationMvpView mvpView) {
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

    public void registrationSubmitted(ParseUser user) {
        checkViewAttached();
        getView().showProgress(true);

        Log.i("Registration: ", "Registration submitted..");

        compositeDisposable.add(getDataManager().signUp(user).subscribeWith(new DisposableObserver<ParseUser>() {
            @Override
            public void onNext(ParseUser parseUser) {

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
                if (!isViewAttached()) {
                    Timber.e("View is not attached.");
                    return;
                }

                getView().showProgress(false);
                getView().showBarListFragment();

            }
        }));
    }

    public void unsubscribe() {
        compositeDisposable.clear();
    }

    public void goToLogin() {
        getView().goToLogin();
    }

}
