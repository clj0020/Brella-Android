package com.madmensoftware.com.ui.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.data.model.response.User;
import com.madmensoftware.com.ui.base.BasePresenter;
import com.madmensoftware.com.injection.ConfigPersistent;
import com.madmensoftware.com.util.SchedulerProvider;
import com.stripe.android.CustomerSession;
import com.stripe.android.model.Customer;

import io.reactivex.disposables.CompositeDisposable;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    @Inject
    public MainPresenter(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void initCustomerSession(User user) {
        CustomerSession.initCustomerSession(
                new BrellaEphemeralKeyProvider(
                        new BrellaEphemeralKeyProvider.ProgressListener() {
                        @Override
                        public void onStringResponse(String string) {
                            if (string.startsWith("Error: ")) {
                                Log.e("Error", string);
                            }
                        }
                }, getDataManager(), user));

        Log.i("MainPresenter", "Customer session initialized.");

    }

    public void showBarListFragment(MainMvpView mainMvpView) {
        mainMvpView.showBarListFragment();
    }

    public void showToolbar(MainMvpView mainMvpView) {
        mainMvpView.showToolbar();
    }

    public void hideToolbar(MainMvpView mainMvpView) {
        mainMvpView.hideToolbar();
    }

    public void showLoginFragment(MainMvpView mainMvpView) {
        mainMvpView.showLoginFragment();
    }

    public void showRegistrationFragment(MainMvpView mainMvpView) {
        mainMvpView.showRegistrationFragment();
    }

    public void showBarDetailFragment(MainMvpView mainMvpView, String barName) {
        mainMvpView.showBarDetailFragment(barName);
    }


    public void retrieveCustomer(CustomerSession.CustomerRetrievalListener customerRetrievalListener) {
        CustomerSession.getInstance().retrieveCurrentCustomer(customerRetrievalListener);
    }
}
