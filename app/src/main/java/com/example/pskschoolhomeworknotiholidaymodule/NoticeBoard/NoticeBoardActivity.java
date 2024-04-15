package com.example.pskschoolhomeworknotiholidaymodule.NoticeBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pskschoolhomeworknotiholidaymodule.Homework.AddHomeworkActivity;
import com.example.pskschoolhomeworknotiholidaymodule.Homework.HomeworkActivity;
import com.example.pskschoolhomeworknotiholidaymodule.R;
import com.example.pskschoolhomeworknotiholidaymodule.UtilityClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public class NoticeBoardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NoticeboardAdapter adapter;
    private List<NoticeBoardDataClass> notificationList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notificationList = new ArrayList<>();
        adapter = new NoticeboardAdapter(notificationList);
        recyclerView.setAdapter(adapter);
        Ion.with(this)
                .load(UtilityClass.BASE_URL+"api/SchoolNotifications/GetSchoolNotifications")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (e != null) {
                            e.printStackTrace();
                            Toast.makeText(NoticeBoardActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        handleJsonArrayResponse(result);
                    }
                });
        FloatingActionButton addEmployeeButton = findViewById(R.id.addEmployeeButton);
        addEmployeeButton.setOnClickListener(view -> {
            // Create an Intent to open the new activity
            Intent intent = new Intent(NoticeBoardActivity.this, AddNoticeActivity.class);
            startActivity(intent);
        });
       // setAdapter();


    }
    private void handleJsonArrayResponse(JsonArray jsonArray) {
        for (JsonElement jsonElement : jsonArray) {
            NoticeBoardDataClass notification = new Gson().fromJson(jsonElement, NoticeBoardDataClass.class);
            notificationList.add(notification);
        }
        adapter.notifyDataSetChanged();
    }
//    private void setAdapter() {
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        noticeboardItemList = new ArrayList<>();
//        addNoticeData();
//        adapter = new NoticeboardAdapter(this, noticeboardItemList);
//        recyclerView.setAdapter(adapter);
//    }
//
//    private void addNoticeData() {
//        // Add sample school notice data
//        noticeboardItemList.add(new NoticeBoardDataClass("Parent-Teacher Meeting", "We invite parents to attend the upcoming parent-teacher meeting on [Date] from [Time] to discuss students' progress.", "School Administration", "04/20/2024"));
//        noticeboardItemList.add(new NoticeBoardDataClass("Annual Sports Day", "Get ready for an exciting day filled with sports events and activities! Annual Sports Day will be held on [Date].", "Physical Education Department", "04/20/2024"));
//        noticeboardItemList.add(new NoticeBoardDataClass("Book Fair", "Visit our school's book fair showcasing a wide range of books for all ages. Don't miss this opportunity to enhance your reading!", "Library Committee", "04/20/2024"));
//        noticeboardItemList.add(new NoticeBoardDataClass("Science Exhibition", "Students are encouraged to participate in the upcoming science exhibition. Showcase your innovative projects and experiments!", "Science Department", "04/20/2024"));
//        noticeboardItemList.add(new NoticeBoardDataClass("Holiday Notice", "Please note that the school will remain closed on [Date] due to [reason]. Classes will resume as usual on [Next Working Day].", "School Administration", "04/20/2024"));
//        noticeboardItemList.add(new NoticeBoardDataClass("Cultural Fest", "Join us for a day of cultural celebration featuring music, dance, drama, and delicious food from various cultures around the world.", "Cultural Committee", "04/20/2024"));
//        noticeboardItemList.add(new NoticeBoardDataClass("Scholarship Announcement", "We are pleased to announce scholarships for outstanding students. Eligible students can apply by [Deadline].", "Scholarship Committee", "04/20/2024"));
//        noticeboardItemList.add(new NoticeBoardDataClass("Recruitment Drive", "Attention seniors! A recruitment drive will be conducted by [Company/Organization] on [Date]. Prepare your resumes and be ready for interviews!", "Career Guidance Counselor", "04/20/2024"));
//        noticeboardItemList.add(new NoticeBoardDataClass("Community Service Initiative", "Join our school's community service initiative to make a positive impact in the local community. Volunteer opportunities available!", "Community Service Committee", "04/20/2024"));
//        noticeboardItemList.add(new NoticeBoardDataClass("Class Photo Day", "Get ready for smiles and poses! Class photo day is scheduled for [Date]. Please ensure neat appearance and punctuality.", "Yearbook Committee", "04/20/2024"));
//// Add more notices as needed...
//
//    }
}