package com.tohsoft.airquality.ui.demo.ranking.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.tohsoft.airquality.R;
import com.tohsoft.airquality.data.models.CityRanking;

import java.util.ArrayList;
import java.util.List;

public class AdapterRanking extends RecyclerView.Adapter<AdapterRanking.Holder> {
    private View.OnClickListener onClickListener;
    private List<CityRanking> cityRankings;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public AdapterRanking() {
        super();
        cityRankings = new ArrayList<>();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ranking, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(cityRankings.get(position), onClickListener);
    }

    @Override
    public int getItemCount() {
        return cityRankings.size();
    }

    public void setData(List<CityRanking> cities) {
        cityRankings = cities;
        if (cityRankings == null) {
            cityRankings = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        AppCompatTextView textView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }

        public void bind(CityRanking ranking, View.OnClickListener onClickListener) {
            textView.setText(ranking.toString());
            textView.setTag(ranking);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClick(v);
                    }
                }
            });
        }
    }
}
