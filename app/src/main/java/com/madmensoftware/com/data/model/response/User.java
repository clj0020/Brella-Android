package com.madmensoftware.com.data.model.response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.parse.ParseClassName;
import com.parse.ParseUser;
import com.stripe.android.model.Token;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by clj00 on 8/30/2017.
 */

@ParseClassName("_User")
public class User extends ParseUser {

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public String getCustomerId() {
        return getString("customerId");
    }

    public void setCustomerId(String customerId) {
        put("customerId", customerId);
    }

}
