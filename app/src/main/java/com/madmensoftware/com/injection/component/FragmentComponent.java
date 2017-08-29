package com.madmensoftware.com.injection.component;

import dagger.Subcomponent;

import com.madmensoftware.com.ui.bar_detail.BarDetailFragment;
import com.madmensoftware.com.ui.bar_list.BarListFragment;
import com.madmensoftware.com.ui.events.EventsFragment;
import com.madmensoftware.com.ui.friends.FriendsFragment;
import com.madmensoftware.com.ui.login.LoginFragment;
import com.madmensoftware.com.ui.navigation.NavigationFragment;
import com.madmensoftware.com.ui.passes.PassesFragment;
import com.madmensoftware.com.ui.registration.RegistrationFragment;
import com.madmensoftware.com.ui.settings.SettingsFragment;
import com.madmensoftware.com.injection.PerFragment;
import com.madmensoftware.com.injection.module.FragmentModule;

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(LoginFragment loginFragment);

    void inject(RegistrationFragment registrationFragment);

    void inject(BarDetailFragment barDetailFragment);

    void inject(BarListFragment barListFragment);

    void inject(NavigationFragment navigationFragment);

    void inject(PassesFragment passesFragment);

    void inject(EventsFragment eventsFragment);

    void inject(SettingsFragment settingsFragment);

    void inject(FriendsFragment friendsFragment);

}
