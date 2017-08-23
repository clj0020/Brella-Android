package com.madmensoftware.com.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.madmensoftware.com.data.model.response.Bar;
import com.madmensoftware.com.data.remote.BarService;
import com.parse.ParseQuery;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import rx.parse2.ParseObservable;

/**
 * Created by shivam on 29/5/17.
 */
@Singleton
public class DataManager {

    private BarService barService;

    @Inject
    public DataManager(BarService barService) {
        this.barService = barService;

    }

    public Observable<List<Bar>> getBarList(int limit) {
        return ParseObservable
                .find(ParseQuery.getQuery(Bar.class).setLimit(limit))
                .toList()
                .toObservable();
    }

    public Observable<Bar> getBar(String name) {
        return ParseObservable.find(ParseQuery.getQuery(Bar.class).whereEqualTo("Name", name));
    }
}
