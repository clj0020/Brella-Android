package com.madmensoftware.com.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import com.madmensoftware.com.R;
import com.madmensoftware.com.data.model.response.User;
import com.madmensoftware.com.ui.add_payment.AddPaymentFragment;
import com.madmensoftware.com.ui.bar_detail.BarDetailFragment;
import com.madmensoftware.com.ui.bar_list.BarListFragment;
import com.madmensoftware.com.ui.base.BaseActivity;
import com.madmensoftware.com.ui.events.EventsFragment;
import com.madmensoftware.com.ui.friends.FriendsFragment;
import com.madmensoftware.com.ui.login.LoginFragment;
import com.madmensoftware.com.ui.navigation.NavigationFragment;
import com.madmensoftware.com.ui.passes.PassesFragment;
import com.madmensoftware.com.ui.registration.RegistrationFragment;
import com.madmensoftware.com.ui.settings.SettingsFragment;
import com.madmensoftware.com.injection.component.ActivityComponent;
import com.parse.ParseUser;
import com.stripe.android.CustomerSession;
import com.stripe.android.model.Customer;


// TODO: Check how the threading is working. It's beginning to hang a little bit.
public class MainActivity extends BaseActivity implements MainMvpView {

    private static final String BACK_STACK_ROOT_TAG = "root_fragment";

    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;

    CustomerSession.CustomerRetrievalListener mCustomerRetrievalListener = new CustomerSession.CustomerRetrievalListener() {
        @Override
        public void onCustomerRetrieved(@NonNull Customer customer) {
            Toast.makeText(MainActivity.this, "Customer retrieved!", Toast.LENGTH_LONG).show();
            Log.i("MainActivity", "customer retrieved: " + customer.getId());
            
        }

        @Override
        public void onError(int errorCode, @Nullable String errorMessage) {
            Toast.makeText(MainActivity.this, "Customer not retrieved!", Toast.LENGTH_LONG).show();
            Log.e("MainActivity", "customer not retrieved: " + errorMessage);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);

        User currentUser = (User) ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            mainPresenter.initCustomerSession(currentUser);
            mainPresenter.retrieveCustomer(mCustomerRetrievalListener);
            mainPresenter.showBarListFragment(this);

        } else {
            // show the signup or login screen
            mainPresenter.showRegistrationFragment(this);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        mainPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mainPresenter.detachView();
    }

    @Override
    public void showLoginFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.findFragmentById(R.id.fragment_container) != null) {
            fragmentManager.popBackStack();

            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .replace(R.id.fragment_container, LoginFragment.newInstance(), LoginFragment.TAG)
                    .commit();
        }
        else {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .add(R.id.fragment_container, LoginFragment.newInstance(), LoginFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void showRegistrationFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.findFragmentById(R.id.fragment_container) != null) {
            fragmentManager.popBackStack();

            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .replace(R.id.fragment_container, RegistrationFragment.newInstance(), RegistrationFragment.TAG)
                    .commit();
        }
        else {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .add(R.id.fragment_container, RegistrationFragment.newInstance(), RegistrationFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void showBarListFragment() {
        // Pop off everything up to and including the current tab
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.findFragmentById(R.id.fragment_container) != null) {
            fragmentManager.popBackStack();

            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .replace(R.id.fragment_container, BarListFragment.newInstance(), BarListFragment.TAG)
                    .commit();
        }
        else {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_left,
                            R.anim.slide_right
                    ).add(
                            R.id.fragment_container,
                            BarListFragment.newInstance(),
                            BarListFragment.TAG
                    )
                    .commit();
        }
    }

    @Override
    public void showBarDetailFragment(String barName) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.grow, R.anim.slide_right)
                .replace(R.id.fragment_container, BarDetailFragment.newInstance(barName), BarDetailFragment.TAG)
                .addToBackStack(BACK_STACK_ROOT_TAG)
                .commit();
    }

    @Override
    public void showPassesFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.grow, R.anim.slide_right)
                .replace(R.id.fragment_container, PassesFragment.newInstance(), PassesFragment.TAG)
                .addToBackStack(BACK_STACK_ROOT_TAG)
                .commit();
    }

    @Override
    public void showEventsFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.grow, R.anim.slide_right)
                .replace(R.id.fragment_container, EventsFragment.newInstance(), EventsFragment.TAG)
                .addToBackStack(BACK_STACK_ROOT_TAG)
                .commit();
    }

    @Override
    public void showFriendsFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.grow, R.anim.slide_right)
                .replace(R.id.fragment_container, FriendsFragment.newInstance(), FriendsFragment.TAG)
                .addToBackStack(BACK_STACK_ROOT_TAG)
                .commit();
    }

    @Override
    public void showSettingsFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.grow, R.anim.slide_right)
                .replace(R.id.fragment_container, SettingsFragment.newInstance(), SettingsFragment.TAG)
                .addToBackStack(BACK_STACK_ROOT_TAG)
                .commit();
    }

    @Override
    public void showNavigationFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.grow, R.anim.slide_right)
                .replace(R.id.fragment_container, NavigationFragment.newInstance(), NavigationFragment.TAG)
                .addToBackStack(BACK_STACK_ROOT_TAG)
                .commit();
    }

    @Override
    public void showAddPaymentFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.grow, R.anim.slide_right)
                .replace(R.id.fragment_container, AddPaymentFragment.newInstance(), AddPaymentFragment.TAG)
                .addToBackStack(BACK_STACK_ROOT_TAG)
                .commit();
    }

    @Override
    public void showToolbar() {
        appBarLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideToolbar() {
        appBarLayout.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();

        // If the back stack has nothing in it when the back button is pressed, then close the application
        if (backStackEntryCount == 0) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
        }
    }

    @Override
    protected void onDestroy() {
        mainPresenter.detachView();
        super.onDestroy();
    }

}
