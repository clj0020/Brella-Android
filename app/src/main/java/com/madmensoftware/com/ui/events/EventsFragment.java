package com.madmensoftware.com.ui.events;

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

public class EventsFragment extends BaseFragment implements EventsMvpView {

    public static final String TAG = "EventsFragment";

    @Inject
    EventsPresenter eventsPresenter;

    @Override
    public int getLayout() {
        return R.layout.fragment_events;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void attachView() {
        eventsPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        eventsPresenter.detachView();
    }


    public static EventsFragment newInstance() {
        Bundle args = new Bundle();
        EventsFragment fragment = new EventsFragment();
        fragment.setArguments(args);

        Log.i("Fragment: ", "EventsFragment created");

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

