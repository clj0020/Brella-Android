package com.madmensoftware.com.data.local;

import com.madmensoftware.com.data.model.response.Bar;
import com.parse.ParseQuery;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import rx.parse2.ParseObservable;

/**
 * Created by shivam on 29/5/17.
 */

// To be implemented with Realm
// TODO: Implement a local storage method. Look into Parse's pinning.
@Singleton
public class DbManager {

    @Inject
    public DbManager() {

    }

    public Observable<List<Bar>> saveBarsToLocalStorage(List<Bar> bars) {
        return ParseObservable.pin(bars)
                .toList()
                .toObservable();
    }

    public Observable<List<Bar>> getBarsFromLocalStorage(int limit) {
        return ParseObservable.find(ParseQuery.getQuery(Bar.class).fromLocalDatastore().setLimit(limit)).toList().toObservable();
    }

    public Observable<Bar> saveBarToLocalStorage(Bar bar) {
        return ParseObservable.pin(bar);
    }

    public Observable<Bar> getBarFromLocalStorage(String name) {
        return ParseObservable.find(ParseQuery.getQuery(Bar.class).fromLocalDatastore().whereEqualTo("Name", name));
    }

}
