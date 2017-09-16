package com.madmensoftware.com.ui.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madmensoftware.com.R;
import com.madmensoftware.com.data.model.response.User;
import com.madmensoftware.com.ui.base.BaseFragment;
import com.madmensoftware.com.injection.component.FragmentComponent;
import com.madmensoftware.com.ui.main.MainActivity;
import com.parse.ParseUser;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by clj00 on 8/24/2017.
 */

public class SettingsFragment extends BaseFragment implements SettingsMvpView {

    public static final String TAG = "SettingsFragment";

    @Inject
    SettingsPresenter settingsPresenter;

    @BindView(R.id.settings_name)
    TextView name;

    @BindView(R.id.recycler_payment_methods)
    RecyclerView paymentMethodsRecycler;

    @OnClick(R.id.settings_log_out)
    void logOut() {
        settingsPresenter.logOut();
    }

    @OnClick(R.id.settings_add_payment_method)
    void addPaymentMethodClicked() {
        ((MainActivity) getActivity()).showAddPaymentFragment();
    }

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

        Timber.i("SettingsFragment created.");

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

        settingsPresenter.showUserInfo();

        return view;
    }


    @Override
    public void showLoginFragment() {
        ((MainActivity) getActivity()).showLoginFragment();
    }

    @Override
    public void showUserInfo(User user) {
        name.setText(user.getName());
    }


}

