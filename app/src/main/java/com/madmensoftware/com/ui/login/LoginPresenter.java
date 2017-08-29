package com.madmensoftware.com.ui.login;

import android.util.Log;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.ui.base.BasePresenter;
import com.madmensoftware.com.injection.ConfigPersistent;
import com.madmensoftware.com.util.SchedulerProvider;
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
 * Created by clj00 on 8/18/2017.
 */
@ConfigPersistent
public class LoginPresenter extends BasePresenter<LoginMvpView> {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public LoginPresenter(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void attachView(LoginMvpView mvpView) {
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

    public void loginSubmitted(String username, String password) {
        checkViewAttached();
        getView().showProgress(true);

        Log.i("Login: ", "Login submitted..");

        compositeDisposable.add(getDataManager()
                .login(username, password)
                .subscribeWith(new DisposableObserver<ParseUser>() {
                    @Override
                    public void onNext(ParseUser parseUser) {
                        Timber.i("ParseUser: " + parseUser.toString());
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

                        Timber.i("Completed. Showing bar list fragment.");

                        getView().showProgress(false);
                        getView().showBarListFragment();
                    }
                }));
    }

    public void unsubscribe() {
        compositeDisposable.clear();
    }

    public void goToRegistration() {
        getView().goToRegistration();
    }


}

