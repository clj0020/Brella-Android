package com.madmensoftware.com.injection.module;

import android.app.Service;

import dagger.Module;

/**
 * Created by clj00 on 9/13/2017.
 */

@Module
public class ServiceModule {

    private final Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

}