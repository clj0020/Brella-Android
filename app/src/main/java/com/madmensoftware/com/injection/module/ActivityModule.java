package com.madmensoftware.com.injection.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import com.madmensoftware.com.injection.ActivityContext;
import com.madmensoftware.com.ui.bar_list.BarListMvpView;
import com.madmensoftware.com.ui.bar_list.BarListPresenter;
import com.madmensoftware.com.util.AppSchedulerProvider;
import com.madmensoftware.com.util.SchedulerProvider;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return activity;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

}
