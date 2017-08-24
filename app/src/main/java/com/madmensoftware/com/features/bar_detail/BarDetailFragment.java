package com.madmensoftware.com.features.bar_detail;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.madmensoftware.com.R;
import com.madmensoftware.com.data.model.response.Bar;
import com.madmensoftware.com.features.base.BaseFragment;
import com.madmensoftware.com.features.common.ErrorView;
import com.madmensoftware.com.features.main.MainActivity;
import com.madmensoftware.com.injection.component.FragmentComponent;
import com.madmensoftware.com.util.QrCodeHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarDetailFragment extends BaseFragment implements BarDetailMvpView, ErrorView.ErrorListener {

    public static final String TAG = "BarDetailFragment";
    public static final String EXTRA_BAR_NAME = "EXTRA_POKEMON_NAME";

    @Inject
    BarDetailPresenter barDetailPresenter;

    @BindView(R.id.view_error)
    ErrorView errorView;

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

    @BindView(R.id.layout_stats)
    LinearLayout statLayout;

    @BindView(R.id.layout_bar)
    View pokemonLayout;

    private String barName;

    @Override
    public int getLayout() {
        return R.layout.fragment_bar_detail;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void attachView() {
        barDetailPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        barDetailPresenter.detachView();
    }


    public static BarDetailFragment newInstance(String barName) {
        Bundle args = new Bundle();
        args.putString(EXTRA_BAR_NAME, barName);

        BarDetailFragment fragment = new BarDetailFragment();
        fragment.setArguments(args);

        Log.i("Fragment", "BarDetailFragment created..");

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        barName = getArguments() != null ? getArguments().getString(EXTRA_BAR_NAME) : "";

        if (barName == null) {
            throw new IllegalArgumentException("Detail Activity requires a pokemon name@");
        }
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

        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        ((MainActivity) getActivity()).showToolbar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(barName.substring(0, 1).toUpperCase() + barName.substring(1));


        errorView.setErrorListener(this);

        barDetailPresenter.getBar(barName);

        return view;
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
        barDetailPresenter.getBar(barName);
    }

    public BarDetailFragment() {
        // Required empty public constructor
    }



}
