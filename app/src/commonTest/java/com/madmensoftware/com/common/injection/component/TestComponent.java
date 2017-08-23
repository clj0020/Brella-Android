package com.madmensoftware.com.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import com.madmensoftware.com.common.injection.module.ApplicationTestModule;
import com.madmensoftware.com.injection.component.AppComponent;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends AppComponent {
}
