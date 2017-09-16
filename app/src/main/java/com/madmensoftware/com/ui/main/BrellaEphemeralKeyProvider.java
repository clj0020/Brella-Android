package com.madmensoftware.com.ui.main;

import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.util.Log;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.data.model.response.User;
import com.madmensoftware.com.data.remote.StripeService;
import com.stripe.android.EphemeralKeyProvider;
import com.stripe.android.EphemeralKeyUpdateListener;


import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by clj00 on 9/15/2017.
 */

public class BrellaEphemeralKeyProvider implements EphemeralKeyProvider {

    private @NonNull
    CompositeDisposable mCompositeDisposable;
    private @NonNull
    DataManager mDataManager;
    private @NonNull ProgressListener mProgressListener;

    private @NonNull User mUser;

    public BrellaEphemeralKeyProvider(@NonNull ProgressListener progressListener, DataManager dataManager, User user) {
        mDataManager = dataManager;
        mUser = user;
        mCompositeDisposable = new CompositeDisposable();
        mProgressListener = progressListener;

        Log.i("EphemeralKeyProvider", "Key provider created.");
    }

    @Override
    public void createEphemeralKey(@NonNull @Size(min = 4) String apiVersion,
                                   @NonNull final EphemeralKeyUpdateListener keyUpdateListener) {
        Map<String, String> apiParamMap = new HashMap<>();
        apiParamMap.put("customerId", mUser.getCustomerId());
        apiParamMap.put("api_version", apiVersion);

        mCompositeDisposable.add(
                mDataManager.createEphemeralKey(apiParamMap)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<HashMap>() {
                            @Override
                            public void onNext(HashMap s) {
                                Log.i("EphemeralKeyProvider: ", "Response from creating ephemeral key: " + s.toString());
                                String rawKey = s.toString();
                                keyUpdateListener.onKeyUpdate(rawKey);
                                mProgressListener.onStringResponse(rawKey);
                            }

                            @Override
                            public void onError(Throwable e) {
                                mProgressListener.onStringResponse(e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        }));
    }

    public interface ProgressListener {
        void onStringResponse(String string);
    }
}
