package com.tohsoft.airquality.ui.demo.free.history.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.tohsoft.airquality.R;
import com.tohsoft.airquality.data.models.GraphModel;
import com.tohsoft.airquality.forecast.Pair;

import java.util.ArrayList;
import java.util.List;

public class AdapterHourHistory extends RecyclerView.Adapter<AdapterHourHistory.Holder> {
    private List<Pair<Integer, Double>> pairs = new ArrayList<>();
    private int count;
    private GraphModel graphModel;
    private int day;

    public AdapterHourHistory() {
        this.pairs = new ArrayList<>();
    }

    public void updateData(GraphModel graphModel) {
        this.graphModel = graphModel;
        this.pairs = graphModel.data();
        if (this.pairs == null) {
            this.pairs = new ArrayList<>();
        }
        for (Pair<Integer, Double> pair : this.pairs) {
            count = Math.max(count, pair.getLeft());
            day = count / 24;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterHourHistory.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hour_history, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHourHistory.Holder holder, int position) {
        Pair<Integer, Double> pair = getChildData(position);
        holder.bind(pair, graphModel, count - position, position);
    }

    private Pair<Integer, Double> getChildData(int position) {
        for (Pair<Integer, Double> pair : this.pairs) {
            if (pair.getLeft() == position) {
                return pair;
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return count;
    }

    int timeStart;
    int current;

    public class Holder extends RecyclerView.ViewHolder {
        AppCompatTextView value;
        AppCompatTextView hour;


        public Holder(@NonNull View itemView) {
            super(itemView);
            value = itemView.findViewById(R.id.value);
            hour = itemView.findViewById(R.id.time);
        }

        public void bind(Pair<Integer, Double> pair, GraphModel graphModel, int pos, int count) {
            if (pair == null) {
                value.setText("");
                hour.setText("");
                itemView.setVisibility(View.INVISIBLE);
                return;
            }
            itemView.setVisibility(View.VISIBLE);
            value.setText(pair.getRight() + "");
            int time = graphModel.hStart()-(pair.getLeft()+graphModel.hOffset());
            while (time < 0) {
                time += 24;
            }
            hour.setText(time==0?graphModel.getDay(pair.getLeft()):time +"");
        }
    }
}
