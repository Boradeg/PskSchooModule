package com.example.pskschoolhomeworknotiholidaymodule.Attendance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pskschoolhomeworknotiholidaymodule.R;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {

    private List<AttendanceData> attendanceList;

    public AttendanceAdapter(List<AttendanceData> attendanceList) {
        this.attendanceList = attendanceList;
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attendance, parent, false);
        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, int position) {
        AttendanceData attendanceData = attendanceList.get(position);

       // holder.tv_attendance_std.setText("Teacher: " + attendanceData.getStandardName());

        holder.tv_attendance_std.setText("Standard : " + attendanceData.getStandardName());
        holder.tv_attendance_div.setText(attendanceData.getDivisionName());
       holder.tv_attendance_teacher_name.setText(attendanceData.getTeacherFullName());
        holder.tv_attendance_date.setText(attendanceData.getOnDate());
       holder.tv_total_absent_item.setText(String.valueOf(attendanceData.getNoOfAbsents()));
        holder.tv_total_present_item.setText(String.valueOf(attendanceData.getNoOfPresents()));
        holder.tv_total_not_available_item.setText(String.valueOf(attendanceData.getNoOfNotAvailables()));
    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    public class AttendanceViewHolder extends RecyclerView.ViewHolder {

        TextView tv_total_not_available_item,tv_total_present_item,tv_attendance_date, tv_total_absent_item,tvTeacher,tv_attendance_std,tv_attendance_div,tv_attendance_teacher_name;

        public AttendanceViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_attendance_std = itemView.findViewById(R.id.tv_attendance_std);
            tv_attendance_div = itemView.findViewById(R.id.tv_attendance_div);
            tv_attendance_teacher_name = itemView.findViewById(R.id.tv_attendance_teacher_name);
            tv_attendance_date = itemView.findViewById(R.id.tv_attendance_date);
            tv_total_absent_item = itemView.findViewById(R.id.tv_total_absent_item);
            tv_total_present_item = itemView.findViewById(R.id.tv_total_present_item);
            tv_total_not_available_item = itemView.findViewById(R.id.tv_total_not_available_item);
        }
    }
}
