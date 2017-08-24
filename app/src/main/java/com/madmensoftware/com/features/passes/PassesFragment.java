package com.madmensoftware.com.features.passes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.madmensoftware.com.R;
import com.madmensoftware.com.features.base.BaseFragment;
import com.madmensoftware.com.features.main.MainActivity;
import com.madmensoftware.com.features.navigation.NavigationFragment;
import com.madmensoftware.com.features.navigation.NavigationMvpView;
import com.madmensoftware.com.features.navigation.NavigationPresenter;
import com.madmensoftware.com.injection.component.FragmentComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by clj00 on 8/24/2017.
 */

public class PassesFragment extends BaseFragment implements PassesMvpView {

    public static final String TAG = "PassesFragment";

    @Inject
    PassesPresenter passesPresenter;

    @Override
    public int getLayout() {
        return R.layout.fragment_passes;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void attachView() {
        passesPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        passesPresenter.detachView();
    }


    public static PassesFragment newInstance() {
        Bundle args = new Bundle();
        PassesFragment fragment = new PassesFragment();
        fragment.setArguments(args);

        Log.i("Fragment: ", "PassesFragment created");

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

