package com.example.android.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fifiv on 09/01/2018.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherAdapterViewHolder> {

    private Context mContext;

    public WeatherAdapter(Context context) {
        mContext = context;
    }

    @Override
    public WeatherAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutItem = R.layout.weather_list_item;
        boolean attachToParentImmediately = false;

        View view = inflater.inflate(layoutItem, parent, attachToParentImmediately);

        WeatherAdapterViewHolder viewHolder = new WeatherAdapterViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class WeatherAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView weatherIcon;

        TextView weatherDateView;
        TextView weatherDescriptionView;
        TextView highTempView;
        TextView lowTempView;

        public WeatherAdapterViewHolder(View itemView) {
            super(itemView);

            weatherIcon = itemView.findViewById(R.id.weather_icon);
            weatherDateView = itemView.findViewById(R.id.weather_date);
            weatherDescriptionView = itemView.findViewById(R.id.weather_description);
            highTempView = itemView.findViewById(R.id.high_temperature);
            lowTempView = itemView.findViewById(R.id.low_temperature);

        }
    }

}
