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

}