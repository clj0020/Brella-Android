package com.madmensoftware.com.injection.component;

import dagger.Subcomponent;
import com.madmensoftware.com.features.detail.DetailActivity;
import com.madmensoftware.com.features.login.LoginActivity;
import com.madmensoftware.com.features.main.MainActivity;
import com.madmensoftware.com.injection.PerActivity;
import com.madmensoftware.com.injection.module.ActivityModule;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(DetailActivity detailActivity);

}
