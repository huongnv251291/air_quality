package com.tohsoft.airquality.ui.demo.free.history.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tohsoft.airquality.R;
import com.tohsoft.airquality.data.models.GraphModel;

import java.util.ArrayList;
import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.Holder> {
    private List<GraphModel> modelList;

    public AdapterHistory() {
        modelList = new ArrayList<>();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_source, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        GraphModel graphModel = modelList.get(position);
        holder.bind(graphModel, position);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void updateListGraphModel(List<GraphModel> graphModels) {
        this.modelList = graphModels;
        if (modelList == null) {
            modelList = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        AppCompatTextView textView;
        AdapterHourHistory adapterHourHistory;

        public Holder(@NonNull View itemView) {
            super(itemView);
            adapterHourHistory = new AdapterHourHistory();
            textView = itemView.findViewById(R.id.tv_name);
            recyclerView = itemView.findViewById(R.id.list_house_history);
            LinearLayoutManager linearLayoutManager=  new LinearLayoutManager(itemView.getContext(), RecyclerView.HORIZONTAL, true);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapterHourHistory);
        }

        public void bind(GraphModel graphModel, int position) {
            textView.setText(graphModel.polID());
            adapterHourHistory.updateData(graphModel);
        }
    }
}
