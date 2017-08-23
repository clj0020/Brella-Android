package com.madmensoftware.com.features.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.madmensoftware.com.R;
import com.madmensoftware.com.features.base.BaseActivity;
import com.madmensoftware.com.features.common.ErrorView;
import com.madmensoftware.com.features.main.MainActivity;
import com.madmensoftware.com.injection.component.ActivityComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

public class LoginActivity extends BaseActivity implements LoginMvpView, ErrorView.ErrorListener {

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

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);


        errorView.setErrorListener(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        loginPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        loginPresenter.detachView();
    }

    @OnClick(R.id.login_submit)
    void onClick() {
        loginPresenter.loginSubmitted(this);
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
    public void navigateToHomeScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onReloadData() {

    }
}
