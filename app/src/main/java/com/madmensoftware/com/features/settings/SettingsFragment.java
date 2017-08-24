package com.madmensoftware.com.features.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madmensoftware.com.R;
import com.madmensoftware.com.features.base.BaseFragment;
import com.madmensoftware.com.features.passes.PassesMvpView;
import com.madmensoftware.com.features.passes.PassesPresenter;
import com.madmensoftware.com.injection.component.FragmentComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by clj00 on 8/24/2017.
 */

public class SettingsFragment extends BaseFragment implements SettingsMvpView {

    public static final String TAG = "SettingsFragment";

    @Inject
    SettingsPresenter settingsPresenter;

    @Override
    public int getLayout() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void attachView() {
        settingsPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        settingsPresenter.detachView();
    }


    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);

        Log.i("Fragment: ", "SettingsFragment created");

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

        return view;
    }



}

