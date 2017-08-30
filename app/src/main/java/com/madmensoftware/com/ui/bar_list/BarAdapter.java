package com.madmensoftware.com.ui.bar_list;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.madmensoftware.com.R;
import com.madmensoftware.com.data.model.response.Bar;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by clj00 on 8/23/2017.
 */

public class BarAdapter extends RecyclerView.Adapter<com.madmensoftware.com.ui.bar_list.BarAdapter.BarListViewHolder> {

    private List<Bar> barList;
    private Subject<String> barClickSubject;
    private Context context;

    @Inject
    BarAdapter() {
        barClickSubject = PublishSubject.create();
        barList = Collections.emptyList();
    }

    public void setBar(List<Bar> bars) {
        this.barList = bars;
        notifyDataSetChanged();
    }

    @Override
    public com.madmensoftware.com.ui.bar_list.BarAdapter.BarListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_bar, parent, false);
        return new com.madmensoftware.com.ui.bar_list.BarAdapter.BarListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(com.madmensoftware.com.ui.bar_list.BarAdapter.BarListViewHolder holder, int position) {
        Bar bar = this.barList.get(position);
        holder.onBind(bar.getName(), bar.getAddress());

        if (bar.getBackgroundImageLink() != null) {
            Log.i("BarAdapter", "Bar " + bar.getName() + " found backgroundLink: " + bar.getBackgroundImageLink());

            Glide.with(context)
                    .load(bar.getBackgroundImageLink())
                    .into(holder.barBackground);
        }
        else {
            // make sure Glide doesn't load anything into this view until told otherwise
            Glide.clear(holder.barBackground);
        }

    }

    @Override
    public int getItemCount() {
        return barList.size();
    }

    Observable<String> getBarClick() {
        return barClickSubject;
    }

    class BarListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_name)
        TextView nameText;

        @BindView(R.id.text_tagline)
        TextView taglineText;

        @BindView(R.id.bar_background)
        ImageView barBackground;

        private String barName;
        private String barTagline;

        BarListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> barClickSubject.onNext(barName));
        }

        void onBind(String barName, String barTagline) {
            this.barName = barName;
            this.barTagline = barTagline;

            nameText.setText(
                    String.format(
                            "%s%s", barName.substring(0, 1).toUpperCase(), barName.substring(1)));

            taglineText.setText(barTagline);
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
