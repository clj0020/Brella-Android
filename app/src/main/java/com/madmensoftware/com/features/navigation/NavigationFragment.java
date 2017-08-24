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

/**
 * Created by clj00 on 8/23/2017.
 */

public class NavigationFragment extends BaseFragment implements NavigationMvpView {

    public static final String TAG = "NavigationFragment";

    @Inject
    NavigationPresenter navigationPresenter;

    @BindView(R.id.navigation_home)
    Button navigationHome;

    @BindView(R.id.navigation_passes)
    Button navigationPasses;

    @BindView(R.id.navigation_events)
    Button navigationEvents;

    @BindView(R.id.navigation_bars)
    Button navigationBars;

    @BindView(R.id.navigation_friends)
    Button navigationFriends;

    @BindView(R.id.navigation_settings)
    Button navigationSettings;

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



}
