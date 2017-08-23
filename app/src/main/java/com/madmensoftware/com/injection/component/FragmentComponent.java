package com.madmensoftware.com.injection.component;

import dagger.Subcomponent;
import com.madmensoftware.com.injection.PerFragment;
import com.madmensoftware.com.injection.module.FragmentModule;

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
}
