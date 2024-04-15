package com.example.pskschoolhomeworknotiholidaymodule.Holiday;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pskschoolhomeworknotiholidaymodule.R;

import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.ViewHolder> {

    private List<Holiday> holidayList;

    public HolidayAdapter(List<Holiday> holidayList) {
        this.holidayList = holidayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoilday, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Holiday holiday = holidayList.get(position);
        holder.bind(holiday);
    }

    @Override
    public int getItemCount() {
        return holidayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_holiday_title_item;
        private TextView tv_holiday_desc_item;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_holiday_title_item = itemView.findViewById(R.id.tv_holiday_title_item);
            tv_holiday_desc_item = itemView.findViewById(R.id.tv_holiday_desc_item);
        }

        public void bind(Holiday holiday) {
            tv_holiday_title_item.setText("Holiday On "+holiday.getCreatedOn());
            tv_holiday_desc_item.setText(holiday.getNote());
        }
    }
}
