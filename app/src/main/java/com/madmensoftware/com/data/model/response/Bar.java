package com.madmensoftware.com.data.model.response;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.List;

@ParseClassName("Bar")
public class Bar extends ParseObject {

    public String getName() {
        return getString("Name");
    }

    public void setName(String name) {
        put("Name", name);
    }

    public String getAddress() {
        return getString("Address");
    }

    public void setAddress(String address) {
        put("Address", address);
    }

    public Number getPhone() {
        return getNumber("Phone");
    }

    public void setPhone(Number phone) {
        put("Phone", phone);
    }

    public void setBackgroundImageLink(String link) {
        put("BackgroundImageLink", link);
    }

    public String getBackgroundImageLink() {
        return getString("BackgroundImageLink");
    }

}

