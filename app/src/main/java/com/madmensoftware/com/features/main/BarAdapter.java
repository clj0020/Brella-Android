package com.madmensoftware.com.features.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.madmensoftware.com.R;
import com.madmensoftware.com.data.model.response.Bar;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class BarAdapter extends RecyclerView.Adapter<BarAdapter.BarViewHolder> {

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
    public BarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_bar, parent, false);
        return new BarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BarViewHolder holder, int position) {
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

    class BarViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_name)
        TextView nameText;

        private String bar;

        BarViewHolder(View itemView) {
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
