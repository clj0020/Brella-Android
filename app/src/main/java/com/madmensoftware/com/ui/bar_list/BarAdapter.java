package com.madmensoftware.com.ui.bar_list;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        holder.onBind(bar.getName());
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

        private String bar;

        BarListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> barClickSubject.onNext(bar));
        }

        void onBind(String bar) {
            this.bar = bar;
            nameText.setText(
                    String.format(
                            "%s%s", bar.substring(0, 1).toUpperCase(), bar.substring(1)));
        }
    }
}
