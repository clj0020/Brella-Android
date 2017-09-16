package com.madmensoftware.com.data.remote;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import rx.parse2.ParseObservable;

/**
 * Created by clj00 on 8/30/2017.
 */

public class StripeService {


    @Inject
    public StripeService() {

    }

    public Observable<HashMap> createEphemeralKey(Map<String, String> apiParamMap) {
        return ParseObservable.callFunction("CreateEphemeralKey", apiParamMap);
    }

//    public Observable<String> createEphemeralKey(Map<String, String> apiParamMap) {
//        return ParseObservable.callFunction("CreateCustomer", apiParamMap);
//
//    }


//    public Token addPaymentMethod(Card card, Context context) {
//        Stripe stripe = new Stripe(context, "pk_test_6pRNASCoBOKtIshFeQd4XMUh");
//        stripe.createToken(
//                card,
//                new TokenCallback() {
//                    public void onSuccess(Token token) {
//                        // Send token to your server
//
//                    }
//                    public void onError(Exception error) {
//                        // Show localized error message
//
//                    }
//                }
//        );
//
//    }

}
