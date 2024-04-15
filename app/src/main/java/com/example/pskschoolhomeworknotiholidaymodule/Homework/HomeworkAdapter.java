package com.example.pskschoolhomeworknotiholidaymodule.Homework;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pskschoolhomeworknotiholidaymodule.R;

import java.util.List;

public class HomeworkAdapter extends RecyclerView.Adapter<HomeworkAdapter.ViewHolder> {

    private List<Homework> homeworkList;

    public HomeworkAdapter(List<Homework> homeworkList) {
        this.homeworkList = homeworkList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homework, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Homework homework = homeworkList.get(position);
        holder.tv_homework_subject_name.setText(homework.getSubjectName());
        holder.tv_homework_user_name.setText(homework.getTeacherFullName());
        holder.tv_homework_date_item.setText(homework.getModifiedOn());
        holder.tv_homework_desc.setText(homework.getNote());
    }


    @Override
    public int getItemCount() {
        return homeworkList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_homework_subject_name;
        private TextView tv_homework_user_name;
        private TextView tv_homework_date_item;
        private TextView tv_homework_desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_homework_subject_name = itemView.findViewById(R.id.tv_homework_subject_name);
            tv_homework_user_name = itemView.findViewById(R.id.tv_homework_user_name);
            tv_homework_date_item = itemView.findViewById(R.id.tv_homework_date_item);
            tv_homework_desc = itemView.findViewById(R.id.tv_homework_desc);
        }

    }
}
