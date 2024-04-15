package com.example.pskschoolhomeworknotiholidaymodule.NoticeBoard;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.pskschoolhomeworknotiholidaymodule.R;

import java.util.List;

public class NoticeboardAdapter extends RecyclerView.Adapter<NoticeboardAdapter.ViewHolder> {

    private List<NoticeBoardDataClass> notificationList;

    public NoticeboardAdapter(List<NoticeBoardDataClass> notificationList) {
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice_board, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoticeBoardDataClass notification = notificationList.get(position);
        holder.bind(notification);
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_notice_title;
        private TextView tv_noticeboard_desc;
        private TextView tvNoticeboardUserName;
        private TextView tv_noticeboard_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_notice_title = itemView.findViewById(R.id.tv_notice_title);
            tv_noticeboard_desc = itemView.findViewById(R.id.tv_noticeboard_desc);
          //  tvNoticeboardUserName = itemView.findViewById(R.id.tv_noticeboard_user_name);
            tv_noticeboard_date = itemView.findViewById(R.id.tv_noticeboard_date);
        }

        public void bind(NoticeBoardDataClass notification) {
            tv_notice_title.setText(notification.getTitle());
            tv_noticeboard_desc.setText(notification.getNote());
           // tvNoticeboardUserName.setText(notification.getCreatedOn());
            tv_noticeboard_date.setText(notification.getModifiedOn());
        }
    }
}


