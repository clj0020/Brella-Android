package com.madmensoftware.com.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.madmensoftware.com.data.local.DbManager;
import com.madmensoftware.com.data.model.response.Bar;
import com.madmensoftware.com.data.remote.ParseService;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import rx.parse2.ParseObservable;


/**
 * Created by shivam on 29/5/17.
 */
@Singleton
public class DataManager {

    private ParseService parseService;
    private final DbManager mDataManager;

    @Inject
    public DataManager(ParseService parseService,
                       DbManager dataManager) {
        this.parseService = parseService;
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

    public Observable<ParseUser> signUp(ParseUser user) {
        return parseService.signUp(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
