package com.example.assignment.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assignment.R;
import com.example.assignment.model.Wheather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HourAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<Wheather> list;

    public HourAdapter(Activity activity, List<Wheather> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_hour, parent, false);
        HourHolder holder = new HourHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HourHolder hn = (HourHolder) holder;
        Wheather wheather = list.get(position);
        hn.tvTime.setText(convertTime(wheather.getDateTime()));
        hn.tvTem.setText(wheather.getTemperature().getValue()+"");
        String url = "";
        if (wheather.getWeatherIcon() < 10){
            url = "https://developer.accuweather.com/sites/default/files/0" + wheather.getWeatherIcon()+ "-s.png";
        }else {
            url = "https://developer.accuweather.com/sites/default/files/" + wheather.getWeatherIcon()+ "-s.png";
        }
        Glide.with(activity).load(url).into(hn.icon);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class HourHolder extends RecyclerView.ViewHolder{
        private TextView tvTime, tvTem;
        private ImageView icon;
        public HourHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvTem = (TextView) itemView.findViewById(R.id.tvTem);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
    public String convertTime(String inputTime) {
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = inFormat.parse(inputTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("ha");
        String goal = outFormat.format(date);
        return goal;
    }


}
