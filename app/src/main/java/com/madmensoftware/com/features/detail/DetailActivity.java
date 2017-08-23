package com.madmensoftware.com.features.detail;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;

import com.google.zxing.WriterException;
import com.madmensoftware.com.R;
import com.madmensoftware.com.data.model.response.Bar;
import com.madmensoftware.com.features.base.BaseActivity;
import com.madmensoftware.com.features.common.ErrorView;
import com.madmensoftware.com.features.detail.dialog.QRCodeDialog;
import com.madmensoftware.com.injection.component.ActivityComponent;
import com.madmensoftware.com.util.QrCodeHelper;

import butterknife.OnClick;
import timber.log.Timber;

public class DetailActivity extends BaseActivity implements DetailMvpView, ErrorView.ErrorListener {

    public static final String EXTRA_POKEMON_NAME = "EXTRA_POKEMON_NAME";

    @Inject
    DetailPresenter detailPresenter;

    @BindView(R.id.view_error)
    ErrorView errorView;

    @BindView(R.id.image_bar)
    ImageView pokemonImage;

    @BindView(R.id.image_qr_code)
    ImageView qrCode;

    @BindView(R.id.text_view_address)
    TextView address;

    @BindView(R.id.text_view_phone)
    TextView phone;

    @BindView(R.id.show_qr_dialog_fab)
    FloatingActionButton showQRFab;

    @BindView(R.id.progress)
    ProgressBar progress;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.layout_stats)
    LinearLayout statLayout;

    @BindView(R.id.layout_bar)
    View pokemonLayout;

    private String barName;

    public static Intent getStartIntent(Context context, String pokemonName) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_POKEMON_NAME, pokemonName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        barName = getIntent().getStringExtra(EXTRA_POKEMON_NAME);
        if (barName == null) {
            throw new IllegalArgumentException("Detail Activity requires a pokemon name@");
        }

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle(barName.substring(0, 1).toUpperCase() + barName.substring(1));

        errorView.setErrorListener(this);

        detailPresenter.getBar(barName);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        detailPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        detailPresenter.detachView();
    }

    @Override
    public void showBar(Bar bar) {
//        if (bar.sprites != null && bar.sprites.frontDefault != null) {
//            Glide.with(this).load(bar.sprites.frontDefault).into(pokemonImage);
//        }

        Log.i("Bar Address", bar.getAddress() + "");
        Log.i("Bar Name", bar.getName() + "");
        Log.i("Bar Phone", bar.getPhone() + "");

        address.setText(bar.getAddress());
        phone.setText(bar.getPhone() + "");

        pokemonLayout.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.show_qr_dialog_fab)
    void onClick() {
        detailPresenter.showQRFabClicked(this, barName);
    }

    @Override
    public void showQrCodeDialog(String key) {
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);


        QRCodeDialog newFragment = QRCodeDialog.newInstance(key);
        newFragment.show(ft, "dialog");
    }


    @Override
    public void showQrCode(String key) {
        QrCodeHelper qrCodeHelper = new QrCodeHelper();

        try {
            Bitmap bitmap = qrCodeHelper.encodeAsBitmap(key);
            qrCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showProgress(boolean show) {
        errorView.setVisibility(View.GONE);
        progress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(Throwable error) {
        pokemonLayout.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        Timber.e(error, "There was a problem retrieving the pokemon...");
    }

    @Override
    public void onReloadData() {
        detailPresenter.getBar(barName);
    }
}
