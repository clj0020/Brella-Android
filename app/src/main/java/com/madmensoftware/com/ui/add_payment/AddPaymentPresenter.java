package com.madmensoftware.com.ui.add_payment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.data.model.response.User;
import com.madmensoftware.com.injection.ConfigPersistent;
import com.madmensoftware.com.ui.base.BasePresenter;
import com.madmensoftware.com.util.SchedulerProvider;
import com.stripe.android.CustomerSession;
import com.stripe.android.SourceCallback;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Source;
import com.stripe.android.model.SourceParams;
import com.stripe.android.model.Token;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by clj00 on 8/30/2017.
 */

@ConfigPersistent
public class AddPaymentPresenter extends BasePresenter<AddPaymentMvpView> {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public AddPaymentPresenter(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void attachView(AddPaymentMvpView mvpView) {
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

    public void addPaymentSubmitted(Card card, User user, Context context) {
        checkViewAttached();
        getView().showProgress(true);

        Stripe stripe = new Stripe(context, "pk_test_wduXHmOxDK0pjNLhLKA86PNK");

        SourceParams cardSourceParams = SourceParams.createCardParams(card);

        stripe.createSource(cardSourceParams, new SourceCallback() {
            @Override
            public void onError(Exception error) {
                getView().showProgress(false);
                Log.e("Error: ", error.getMessage());
            }

            @Override
            public void onSuccess(Source source) {
                getView().showProgress(false);
                Log.i("AddPayment: ", "Source created, adding to customer..");

                CustomerSession.getInstance().addCustomerSource(context, source.getId(), source.getType(), new CustomerSession.SourceRetrievalListener() {
                    @Override
                    public void onSourceRetrieved(@NonNull Source source) {
                        Log.i("AddPayment: ", "Source added to customer: " + source.getId());
                    }

                    @Override
                    public void onError(int errorCode, @Nullable String errorMessage) {
                        Log.e("AddPayment: ", "Source not added to customer: " + errorMessage);
                    }
                });
            }
        });

//        stripe.createToken(
//                card,
//                new TokenCallback() {
//                    public void onSuccess(Token token) {
//                        // Send token to your server
//                        compositeDisposable.add(getDataManager()
//                                .sendPaymentTokenToServer(token, user).subscribeWith(new DisposableObserver<User>() {
//                                    @Override
//                                    public void onNext(User user) {
//                                        Log.i("AddPayment: ", "Adding payment method onNext");
//
//                                    }
//
//                                    @Override
//                                    public void onError(Throwable e) {
//                                        Log.e("AddPayment: ", e.getMessage());
//
//
//                                        if (!isViewAttached()) {
//                                            Log.e("AddPayment: ", "View is not attached.");
//                                            return;
//                                        }
//
//                                        getView().showProgress(false);
//                                    }
//
//                                    @Override
//                                    public void onComplete() {
//                                        Log.i("AddPayment: ", " Adding payment method completed.");
//
//                                        if (!isViewAttached()) {
//                                            Log.e("AddPayment: ", "View is not attached.");
//                                            return;
//                                        }
//
//                                        getView().showProgress(false);
//                                        getView().showSettingsFragment();
//                                    }
//                                }));
//                    }
//                    public void onError(Exception error) {
//                        // Show localized error message
//                        Log.e("AddPayment: ", error.getMessage());
//                    }
//                }
//        );

    }

    public void unsubscribe() {
        compositeDisposable.clear();
    }


}

