package com.madmensoftware.com.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.disposables.CompositeDisposable;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.data.remote.ParseService;
import com.madmensoftware.com.injection.ApplicationContext;
import com.madmensoftware.com.injection.module.AppModule;
import com.madmensoftware.com.util.SchedulerProvider;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager apiManager();

    ParseService parseService();

    SchedulerProvider schedulerProvider();

    CompositeDisposable compositeDisposable();

}
