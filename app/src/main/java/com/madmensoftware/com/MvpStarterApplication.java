package com.madmensoftware.com;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.madmensoftware.com.data.model.response.Bar;
import com.madmensoftware.com.injection.component.AppComponent;
import com.madmensoftware.com.injection.component.DaggerAppComponent;
import com.madmensoftware.com.injection.module.AppModule;
import com.madmensoftware.com.injection.module.NetworkModule;
import com.parse.Parse;
import com.parse.ParseObject;
import com.singhajit.sherlock.core.Sherlock;
import com.squareup.leakcanary.LeakCanary;
import com.tspoon.traceur.Traceur;

import timber.log.Timber;

public class MvpStarterApplication extends Application {

    private AppComponent appComponent;

    public static MvpStarterApplication get(Context context) {
        return (MvpStarterApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
//            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
            LeakCanary.install(this);
            Sherlock.init(this);
            Traceur.enableLogging();
        }

        Timber.plant(new Timber.DebugTree());


        // Parse
        Parse.enableLocalDatastore(this);

        ParseObject.registerSubclass(Bar.class);

        Parse.initialize(this);
    }

    public AppComponent getComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .networkModule(new NetworkModule(this))
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }
}
