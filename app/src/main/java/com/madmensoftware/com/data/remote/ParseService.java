package com.madmensoftware.com.data.remote;

import com.madmensoftware.com.data.model.response.Bar;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import rx.parse2.ParseObservable;

@Singleton
public class ParseService {

    @Inject
    public ParseService() {

    }

    public Observable<List<Bar>> getBarList(int limit) {
        return ParseObservable
                .find(ParseQuery.getQuery(Bar.class).setLimit(limit))
                .toList()
                .toObservable();
    }

    public Observable<Bar> getBar(String name) {
        return ParseObservable.find(ParseQuery.getQuery(Bar.class)
                .whereEqualTo("Name", name));
    }

    public Observable<ParseUser> login(String username, String password) {
        return ParseObservable.logIn(username, password);
    }

    public Observable<Void> logout() {
        return ParseObservable.logOut();
    }

    public Observable<ParseUser> signUp(ParseUser user) {
        return ParseObservable.signUp(user);
    }


}
