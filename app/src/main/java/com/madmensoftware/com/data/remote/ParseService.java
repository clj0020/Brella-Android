package com.madmensoftware.com.data.remote;

import com.madmensoftware.com.data.model.response.Bar;
import com.madmensoftware.com.data.model.response.User;
import com.parse.ParseCloud;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.stripe.android.model.Customer;
import com.stripe.android.model.Token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public Observable<String> createCustomer(ParseUser user) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", "");
        params.put("email", user.getEmail());

        return ParseObservable.callFunction("CreateCustomer", params);
    }
//
//    public Observable<User> addCustomerIdToUser(User user, String customerId) {
//        user.setCustomerId(customerId);
//
//        return ParseObservable.save(user);
//    }
//
//    public Observable<User> addPaymentMethodToUser(Token token, User user) {
//        Stripe stripe = new Stripe();
//
//        Customer customer = stripe.Customer.retrieve('cus_xxxxxxxxxx');
//        customer =
//        customer.sources.create(card=card_token)
//
//
////        user.addPaymentMethod(token);
//
//        return ParseObservable.save(user);
//    }


}
