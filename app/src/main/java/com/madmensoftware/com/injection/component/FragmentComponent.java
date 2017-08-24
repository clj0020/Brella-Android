package com.madmensoftware.com.injection.component;

import dagger.Subcomponent;

import com.madmensoftware.com.features.bar_detail.BarDetailFragment;
import com.madmensoftware.com.features.bar_list.BarListFragment;
import com.madmensoftware.com.features.events.EventsFragment;
import com.madmensoftware.com.features.friends.FriendsFragment;
import com.madmensoftware.com.features.login.LoginFragment;
import com.madmensoftware.com.features.navigation.NavigationFragment;
import com.madmensoftware.com.features.passes.PassesFragment;
import com.madmensoftware.com.features.settings.SettingsFragment;
import com.madmensoftware.com.injection.PerFragment;
import com.madmensoftware.com.injection.module.FragmentModule;

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(LoginFragment loginFragment);

    void inject(BarDetailFragment barDetailFragment);

    void inject(BarListFragment barListFragment);

    void inject(NavigationFragment navigationFragment);

    void inject(PassesFragment passesFragment);

    void inject(EventsFragment eventsFragment);

    void inject(SettingsFragment settingsFragment);

    void inject(FriendsFragment friendsFragment);


}
