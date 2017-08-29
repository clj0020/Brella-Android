package com.madmensoftware.com.injection.component;

import dagger.Subcomponent;

import com.madmensoftware.com.ui.main.MainActivity;
import com.madmensoftware.com.injection.PerActivity;
import com.madmensoftware.com.injection.module.ActivityModule;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
