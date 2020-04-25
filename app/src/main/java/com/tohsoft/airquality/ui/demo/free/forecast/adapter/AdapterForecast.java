package com.tohsoft.airquality.ui.demo.free.forecast.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.tohsoft.airquality.R;
import com.tohsoft.airquality.data.models.aqicn.ForecastModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterForecast extends RecyclerView.Adapter<AdapterForecast.Holder> {
    private List<ForecastModel.ForecastListItem> forecastListItems;
    private final int HEADER = 0;
    private final int NORMAL = 1;

    public void setData(ArrayList<ForecastModel.ForecastListItem> entries) {
        forecastListItems = entries;
        notifyDataSetChanged();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private View.OnClickListener onClickListener;

    public AdapterForecast() {
        forecastListItems = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        ForecastModel.ForecastListItem forecastListItem = forecastListItems.get(position);
        return forecastListItem.record == null ? HEADER : NORMAL;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayout(viewType), parent, false);
        return viewType == HEADER ? new HeaderForeCast(view) : new ItemView(view);
    }

    private int getLayout(int viewType) {
        if (viewType == HEADER) {
            return R.layout.item_fore_cast_header;
        } else {
            return R.layout.item_fore_cast_item;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.bind(forecastListItems.get(position), onClickListener);
    }

    @Override
    public int getItemCount() {
        return forecastListItems.size();
    }

    public abstract static class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }

        abstract void bind(ForecastModel.ForecastListItem forecastListItem, View.OnClickListener listener);
    }

    public static class HeaderForeCast extends Holder {
        AppCompatTextView textView;

        public HeaderForeCast(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.header_tv);
        }

        @Override
        void bind(ForecastModel.ForecastListItem forecastListItem, View.OnClickListener listener) {
            @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String strDate = dateFormat.format(forecastListItem.date);
            textView.setText(strDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(v);
                    }
                }
            });
        }
    }

    public static class ItemView extends Holder {
        AppCompatTextView textView;

        public ItemView(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_forecast);
        }

        @Override
        void bind(ForecastModel.ForecastListItem forecastListItem, View.OnClickListener listener) {
            textView.setText(forecastListItem.record.toString());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(v);
                    }
                }
            });
        }
    }
}
