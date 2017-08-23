package com.madmensoftware.com.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.injection.ApplicationContext;
import com.madmensoftware.com.injection.module.AppModule;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager apiManager();
}
