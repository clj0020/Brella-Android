package com.madmensoftware.com.features.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.madmensoftware.com.R;
import com.madmensoftware.com.features.base.BaseFragment;
import com.madmensoftware.com.features.main.MainActivity;
import com.madmensoftware.com.injection.component.FragmentComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by clj00 on 8/23/2017.
 */

public class NavigationFragment extends BaseFragment implements NavigationMvpView {

    public static final String TAG = "NavigationFragment";

    @Inject
    NavigationPresenter navigationPresenter;

    @OnClick(R.id.navigation_home)
    void homeClicked() {
        navigationPresenter.navigateToHome(this);
    }

    @OnClick(R.id.navigation_passes)
    void passedClicked() {
        navigationPresenter.navigateToPasses(this);
    }

    @OnClick(R.id.navigation_events)
    void eventsClicked() {
        navigationPresenter.navigateToEvents(this);
    }

    @OnClick(R.id.navigation_bars)
    void barsClicked() {
        navigationPresenter.navigateToBars(this);
    }

    @OnClick(R.id.navigation_friends)
    void friendsClicked() {
        navigationPresenter.navigateToFriends(this);
    }

    @OnClick(R.id.navigation_settings)
    void settingsClicked() {
        navigationPresenter.navigateToSettings(this);
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void attachView() {
        navigationPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        navigationPresenter.detachView();
    }


    public static NavigationFragment newInstance() {
        Bundle args = new Bundle();
        NavigationFragment fragment = new NavigationFragment();
        fragment.setArguments(args);

        Log.i("Fragment: ", "NavigationFragment created");

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);

        ((MainActivity) getActivity()).hideToolbar();

        return view;
    }


    @Override
    public void navigateToHome() {
        ((MainActivity) getActivity()).showBarListFragment();
    }

    @Override
    public void navigateToPasses() {
        ((MainActivity) getActivity()).showPassesFragment();
    }

    @Override
    public void navigateToEvents() {
        ((MainActivity) getActivity()).showEventsFragment();
    }

    @Override
    public void navigateToBars() {
        ((MainActivity) getActivity()).showBarListFragment();
    }

    @Override
    public void navigateToFriends() {
        ((MainActivity) getActivity()).showFriendsFragment();
    }

    @Override
    public void navigateToSettings() {
        ((MainActivity) getActivity()).showSettingsFragment();
    }
}
