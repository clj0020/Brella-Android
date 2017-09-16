package com.madmensoftware.com.ui.add_payment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.madmensoftware.com.R;
import com.madmensoftware.com.data.model.response.User;
import com.madmensoftware.com.injection.component.FragmentComponent;
import com.madmensoftware.com.ui.bar_detail.BarDetailPresenter;
import com.madmensoftware.com.ui.base.BaseFragment;
import com.madmensoftware.com.ui.main.MainActivity;
import com.parse.ParseUser;
import com.stripe.android.model.Card;
import com.stripe.android.model.SourceParams;
import com.stripe.android.view.CardInputWidget;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by clj00 on 8/30/2017.
 */

public class AddPaymentFragment extends BaseFragment implements AddPaymentMvpView {

    public static final String TAG = "AddPaymentFragment";

    @Inject
    AddPaymentPresenter addPaymentPresenter;

    @BindView(R.id.stripe_card_widget)
    CardInputWidget cardInputWidget;

    @BindView(R.id.add_payment_submit)
    Button submit;

    @BindView(R.id.add_payment_layout)
    LinearLayout addPaymentLayout;

    @BindView(R.id.progress)
    ProgressBar progressBar;



    @OnClick(R.id.add_payment_submit)
    void onSubmit() {
        Card cardToSave = cardInputWidget.getCard();
        if (cardToSave == null) {
//            mErrorDialogHandler.showError("Invalid Card Data");
            return;
        }

        User user = (User) ParseUser.getCurrentUser();

        addPaymentPresenter.addPaymentSubmitted(cardToSave, user, getContext());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_add_payment;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void attachView() {
        addPaymentPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        addPaymentPresenter.detachView();
    }

    public static AddPaymentFragment newInstance() {
        Bundle args = new Bundle();

        AddPaymentFragment fragment = new AddPaymentFragment();
        fragment.setArguments(args);

        Log.i("Fragment", "BarDetailFragment created..");

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        actionBar.setTitle("Add Payment Method");

        addPaymentLayout.setVisibility(View.VISIBLE);

        return view;
    }

    @Override
    public void showSettingsFragment() {
        ((MainActivity) getActivity()).showSettingsFragment();
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            addPaymentLayout.setVisibility(View.GONE);
        }
        else {
            progressBar.setVisibility(View.GONE);
            addPaymentLayout.setVisibility(View.VISIBLE);
        }
    }
}
