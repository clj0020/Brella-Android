package com.madmensoftware.com.ui.login;

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
import com.madmensoftware.com.ui.base.BaseFragment;
import com.madmensoftware.com.ui.common.ErrorView;
import com.madmensoftware.com.ui.main.MainActivity;
import com.madmensoftware.com.injection.component.FragmentComponent;
import com.madmensoftware.com.util.ViewUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class LoginFragment extends BaseFragment implements LoginMvpView, ErrorView.ErrorListener {

    public static final String TAG = "LoginFragment";

    @Inject
    LoginPresenter loginPresenter;

    @BindView(R.id.login_email)
    TextInputEditText loginEmail;

    @BindView(R.id.login_password)
    TextInputEditText loginPassword;

    @BindView(R.id.login_submit)
    Button loginSubmit;

    @BindView(R.id.view_error)
    ErrorView errorView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @OnClick(R.id.login_to_registration)
    void toLogin() {
        loginPresenter.goToRegistration();
    }

    @OnClick(R.id.login_submit)
    void onSubmit() {
        if (validateInputs()) {
            String email = loginEmail.getText().toString();
            String password = loginPassword.getText().toString();

            loginPresenter.loginSubmitted(email, password);
        }
    }


    public static LoginFragment newInstance() {
        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);

        Log.i("LoginFragment", "LoginFragment created");

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

        loginPassword.setOnEditorActionListener(
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
        return R.layout.fragment_login;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void attachView() {
        loginPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        loginPresenter.detachView();
    }

    @Override
    public void showBarListFragment() {
        ((MainActivity) getActivity()).showBarListFragment();
    }

    @Override
    public void goToRegistration() {
        ((MainActivity) getActivity()).showRegistrationFragment();
    }

    @Override
    public void hideKeyboard() {
        ViewUtil.hideKeyboard(getActivity());
    }

    @Override
    public boolean validateInputs() {
        if (ViewUtil.isEmpty(loginEmail)) {
            loginEmail.setError("Email is empty.");

            return false;
        }
        if (ViewUtil.isEmpty(loginPassword)) {
            loginPassword.setError("Password is empty.");

            return false;
        }
        if (!ViewUtil.isValidEmail(loginEmail.getText().toString())) {
            loginEmail.setError("Email is invalid.");

            return false;
        }
        if( !ViewUtil.isValidPassword(loginPassword.getText().toString())) {
            loginPassword.setError("Password is invalid.");

            return false;
        }

        return true;
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
    public void showError(Throwable error) {
        errorView.setVisibility(View.VISIBLE);
        Timber.e(error, "There was an error retrieving the pokemon");
    }

    @Override
    public void onReloadData() {

    }
}
