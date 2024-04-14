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

        private TextView textHolidayId;
        private TextView tv_subject_name;
        private TextView tv_noticeboard_desc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_subject_name = itemView.findViewById(R.id.tv_subject_name);
            tv_noticeboard_desc = itemView.findViewById(R.id.tv_noticeboard_desc);
        }

        public void bind(Holiday holiday) {
            tv_subject_name.setText("Holiday On "+holiday.getCreatedOn());
            tv_noticeboard_desc.setText(holiday.getNote());

        }
    }
}
