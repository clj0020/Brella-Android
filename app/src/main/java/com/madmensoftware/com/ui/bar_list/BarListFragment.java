package com.madmensoftware.com.ui.bar_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.madmensoftware.com.R;
import com.madmensoftware.com.data.model.response.Bar;
import com.madmensoftware.com.ui.base.BaseFragment;
import com.madmensoftware.com.ui.common.ErrorView;
import com.madmensoftware.com.ui.main.*;
import com.madmensoftware.com.injection.component.FragmentComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by clj00 on 8/23/2017.
 */

public class BarListFragment extends BaseFragment implements BarListMvpView, ErrorView.ErrorListener {

    public static final String TAG = "BarDetailFragment";
    private static final int BAR_COUNT = 20;

    @Inject
    BarListPresenter barListPresenter;

    @Inject
    com.madmensoftware.com.ui.bar_list.BarAdapter barAdapter;

    @BindView(R.id.view_error)
    ErrorView errorView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.recycler_bar)
    RecyclerView barRecycler;

    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;


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
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(R.string.app_name);



        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.primary);
        swipeRefreshLayout.setColorSchemeResources(R.color.white);
        swipeRefreshLayout.setOnRefreshListener(() -> barListPresenter.getBars(BAR_COUNT));

        barRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        barRecycler.setAdapter(barAdapter);
        barClicked();
        errorView.setErrorListener(this);

        barListPresenter.getBars(BAR_COUNT);

        return view;
    }


    private void barClicked() {
        Disposable disposable =
                barAdapter
                        .getBarClick()
                        .subscribe(
                                bar ->
                                        ((MainActivity) getActivity()).showBarDetailFragment(bar),
//                                        startActivity(DetailActivity.getStartIntent(getActivity(), bar)),
                                throwable -> {
                                    Timber.e(throwable, "Bar click failed");
                                    Toast.makeText(
                                            getActivity(),
                                            R.string.error_something_bad_happened,
                                            Toast.LENGTH_LONG)
                                            .show();
                                });
        barListPresenter.compositeDisposable.add(disposable);
    }

    @Override
    public void showBars(List<Bar> bars) {
        barAdapter.setBar(bars);
        barRecycler.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_bar_list;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void attachView() {
        barListPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        barListPresenter.detachView();
    }

    public static BarListFragment newInstance() {
        Bundle args = new Bundle();
        BarListFragment fragment = new BarListFragment();
        fragment.setArguments(args);

        Log.i("Fragment", "BarListFragment created...");

        return fragment;
    }

    public BarListFragment() {
        // Required empty public constructor
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            if (barRecycler.getVisibility() == View.VISIBLE
                    && barAdapter.getItemCount() > 0) {
                swipeRefreshLayout.setRefreshing(true);
            } else {
                progressBar.setVisibility(View.VISIBLE);

                barRecycler.setVisibility(View.GONE);
                swipeRefreshLayout.setVisibility(View.GONE);
            }

            errorView.setVisibility(View.GONE);
        } else {
            swipeRefreshLayout.setRefreshing(false);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(Throwable error) {
        barRecycler.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        Timber.e(error, "There was an error retrieving the pokemon");
    }

    @Override
    public void onReloadData() {
        barListPresenter.getBars(BAR_COUNT);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
