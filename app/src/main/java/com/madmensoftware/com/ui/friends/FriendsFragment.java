package com.madmensoftware.com.ui.friends;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madmensoftware.com.R;
import com.madmensoftware.com.ui.base.BaseFragment;
import com.madmensoftware.com.injection.component.FragmentComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by clj00 on 8/24/2017.
 */

public class FriendsFragment extends BaseFragment implements FriendsMvpView {

    public static final String TAG = "FriendsFragment";

    @Inject
    FriendsPresenter friendsPresenter;

    @Override
    public int getLayout() {
        return R.layout.fragment_friends;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void attachView() {
        friendsPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        friendsPresenter.detachView();
    }


    public static FriendsFragment newInstance() {
        Bundle args = new Bundle();
        FriendsFragment fragment = new FriendsFragment();
        fragment.setArguments(args);

        Log.i("Fragment: ", "FriendsFragment created");

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

