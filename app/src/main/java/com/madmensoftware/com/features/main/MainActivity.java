package com.madmensoftware.com.features.main;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import com.madmensoftware.com.R;
import com.madmensoftware.com.features.bar_detail.BarDetailFragment;
import com.madmensoftware.com.features.bar_list.BarListFragment;
import com.madmensoftware.com.features.base.BaseActivity;
import com.madmensoftware.com.features.login.LoginFragment;
import com.madmensoftware.com.features.navigation.NavigationFragment;
import com.madmensoftware.com.injection.component.ActivityComponent;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final String BACK_STACK_ROOT_TAG = "root_fragment";

    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);

        mainPresenter.showBarListFragment(this);
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
                    .addToBackStack("login_fragment")
                    .commit();
        }
        else {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .add(R.id.fragment_container, LoginFragment.newInstance(), LoginFragment.TAG)
                    .addToBackStack("login_fragment")
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
    public void showNavigationFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.grow, R.anim.slide_right)
                .replace(R.id.fragment_container, NavigationFragment.newInstance(), NavigationFragment.TAG)
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

}
