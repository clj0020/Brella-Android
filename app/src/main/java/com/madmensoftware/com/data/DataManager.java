package com.madmensoftware.com.data;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.madmensoftware.com.data.local.DbManager;
import com.madmensoftware.com.data.model.response.Bar;
import com.madmensoftware.com.data.model.response.User;
import com.madmensoftware.com.data.remote.ParseService;
import com.madmensoftware.com.data.remote.StripeService;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import rx.functions.Action1;
import rx.parse2.ParseObservable;


/**
 * Created by shivam on 29/5/17.
 */
@Singleton
public class DataManager {

    private ParseService parseService;
    private final DbManager mDataManager;
    private StripeService stripeService;

    @Inject
    public DataManager(ParseService parseService,
                       StripeService stripeService,
                       DbManager dataManager) {
        this.parseService = parseService;
        this.stripeService = stripeService;
        this.mDataManager = dataManager;
    }


    public Observable<Bar> getBar(String name) {
        return parseService.getBar(name);
    }

    public Observable<List<Bar>> getBarList(int limit) {

        Observable<List<Bar>> localStorageObservable = mDataManager.getBarsFromLocalStorage(limit)
                .filter(bars -> bars.size() > 0)
                .subscribeOn(Schedulers.computation());

        Observable<List<Bar>> networkObservable = parseService.getBarList(limit)
                .map(bars -> {
                    Observable.create(subscriber -> {
                        mDataManager.saveBarsToLocalStorage(bars);
                        subscriber.onComplete();
                    }).subscribeOn(Schedulers.computation()).subscribe();

                    return bars;
                })
                .subscribeOn(Schedulers.io());

        return Observable
                .concat(localStorageObservable, networkObservable)
                .observeOn(AndroidSchedulers.mainThread());
    }

    // Parse User Functions

    public Observable<ParseUser> login(String username, String password) {
        return parseService.login(username, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Void> logout() {
        return parseService.logout()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<String> signUp(User user) {
        return parseService.createCustomer(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<HashMap> createEphemeralKey(Map<String, String> apiParams) {
        return stripeService.createEphemeralKey(apiParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<String> createCustomer(ParseUser user) {
        return parseService.createCustomer(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
