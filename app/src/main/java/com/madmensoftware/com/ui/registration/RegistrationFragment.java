package com.madmensoftware.com.ui.registration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.madmensoftware.com.R;
import com.madmensoftware.com.injection.component.FragmentComponent;
import com.madmensoftware.com.ui.base.BaseFragment;
import com.madmensoftware.com.ui.common.ErrorView;
import com.madmensoftware.com.ui.login.LoginFragment;
import com.madmensoftware.com.ui.login.LoginPresenter;
import com.madmensoftware.com.ui.main.MainActivity;
import com.madmensoftware.com.util.ViewUtil;
import com.parse.ParseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by clj00 on 8/25/2017.
 */

public class RegistrationFragment extends BaseFragment implements RegistrationMvpView, ErrorView.ErrorListener {

    public static final String TAG = "RegistrationFragment";

    @Inject
    RegistrationPresenter registrationPresenter;

    @BindView(R.id.registration_name)
    TextInputEditText registrationName;

    @BindView(R.id.registration_email)
    TextInputEditText registrationEmail;

    @BindView(R.id.registration_password)
    TextInputEditText registrationPassword;

    @OnClick(R.id.registration_submit)
    void onSubmit() {
        if (validInputs()) {
            ParseUser user = new ParseUser();
            user.setUsername(registrationEmail.getText().toString());
            user.setEmail(registrationEmail.getText().toString());
            user.setPassword(registrationPassword.getText().toString());

            registrationPresenter.registrationSubmitted(user);
        }
    }

    @OnClick(R.id.registration_to_login)
    void toLogin() {
        registrationPresenter.goToLogin();
    }

    @BindView(R.id.registration_view_error)
    ErrorView errorView;

    @BindView(R.id.registration_progress)
    ProgressBar progressBar;

    public static RegistrationFragment newInstance() {
        Bundle args = new Bundle();

        RegistrationFragment fragment = new RegistrationFragment();
        fragment.setArguments(args);

        Log.i("Fragment", "RegistrationFragment created");

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);

        ((MainActivity) getActivity()).hideToolbar();

        registrationPassword.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        boolean handled = false;
                        if (actionId == EditorInfo.IME_ACTION_SEND) {
                            onSubmit();
                            handled = true;
                        }
                        return handled;
                    }
                });

        return view;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_registration;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void attachView() {
        registrationPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        registrationPresenter.detachView();
    }

    @Override
    public void goToLogin() {
        ((MainActivity) getActivity()).showLoginFragment();
    }

    @Override
    public void showBarListFragment() {
        ((MainActivity) getActivity()).showBarListFragment();
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            errorView.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideKeyboard() {
        ViewUtil.hideKeyboard(getActivity());
    }

    @Override
    public boolean validInputs() {

        if (ViewUtil.isEmpty(registrationEmail)) {
            registrationEmail.setError("Email is empty.");

            return false;
        }
        if (ViewUtil.isEmpty(registrationPassword)) {
            registrationPassword.setError("Password is empty.");

            return false;
        }
        if (ViewUtil.isEmpty(registrationName)) {
            registrationName.setError("Name is empty.");

            return false;
        }

        if (!ViewUtil.isValidEmail(registrationEmail.getText().toString())) {
            registrationEmail.setError("Email is invalid.");

            return false;
        }
        if( !ViewUtil.isValidPassword(registrationPassword.getText().toString())) {
            registrationPassword.setError("Password is invalid. Please enter a password longer than 6 characters.");

            return false;
        }

        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void showError(Throwable error) {
        errorView.setVisibility(View.VISIBLE);
        Timber.e(error, "There was an error registering..");
    }

    @Override
    public void onReloadData() {

    }
}
