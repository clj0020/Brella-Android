package com.madmensoftware.com.injection.component;

import com.madmensoftware.com.data.remote.ParseService;
import com.madmensoftware.com.injection.PerService;
import com.madmensoftware.com.injection.module.ServiceModule;

import dagger.Component;

/**
 * Created by clj00 on 9/13/2017.
 */

@PerService
@Component(dependencies = AppComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(ParseService service);

}
