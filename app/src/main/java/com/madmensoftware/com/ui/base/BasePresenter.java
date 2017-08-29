package com.madmensoftware.com.ui.base;

import android.util.Log;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.util.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import rx.Observable;
import rx.Single;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that can be
 * accessed from the children classes by calling getView().
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private final DataManager mDataManager;
    private final SchedulerProvider mSchedulerProvider;
//    private CompositeDisposable mCompositeDisposable;

    private T mvpView;

    @Inject
    public BasePresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void attachView(T mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void detachView() {
        Log.i("Presenter: ", "view detached.");

        mvpView = null;
//        if (!mCompositeDisposable.isDisposed()) {
//            mCompositeDisposable.clear();
//        }
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

//    public CompositeDisposable getCompositeDisposable() {
//        if(mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
//            mCompositeDisposable = new CompositeDisposable();
//        }
//
//        return mCompositeDisposable;
//    }

    protected boolean isViewAttached() {
        return mvpView != null;
    }

    protected T getView() {
        return mvpView;
    }

    protected void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

//    public void addDisposable(Disposable disposable) {
//        mCompositeDisposable.add(disposable);
//    }

//    public void unsubscribe() {
//        mCompositeDisposable.clear();
//    }

    private static class MvpViewNotAttachedException extends RuntimeException {
        MvpViewNotAttachedException() {
            super(
                    "Please call Presenter.attachView(MvpView) before"
                            + " requesting data to the Presenter");
        }
    }

    /**
     * Encapsulate the result of an rx Observable. This model is meant to be used by the children
     * presenters to easily keep a reference to the latest loaded result so that it can be easily
     * emitted again when on configuration changes.
     */
    protected static class DataResult<T> {

        private T mData;
        private Throwable mError;

        public DataResult(T data) {
            mData = data;
        }

        public DataResult(Throwable error) {
            mError = error;
        }

        public Single<T> toSingle() {
            if (mError != null) {
                return Single.error(mError);
            }
            return Single.just(mData);
        }

        public Observable<T> toObservable() {
            if (mError != null) {
                return Observable.error(mError);
            }
            return Observable.just(mData);
        }
    }
}
