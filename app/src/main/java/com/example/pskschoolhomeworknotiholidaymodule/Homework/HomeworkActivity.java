package com.example.pskschoolhomeworknotiholidaymodule.Homework;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pskschoolhomeworknotiholidaymodule.R;
import com.example.pskschoolhomeworknotiholidaymodule.UtilityClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.google.gson.JsonArray;

public class HomeworkActivity extends AppCompatActivity {
    private TextView fabLabel;
    private RecyclerView recyclerView;
    private HomeworkAdapter adapter;
    private List<Homework> homeworkList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);

        FloatingActionButton addEmployeeButton = findViewById(R.id.addEmployeeButton);
        addEmployeeButton.setOnClickListener(view -> {
            // Create an Intent to open the new activity
            Intent intent = new Intent(HomeworkActivity.this, AddHomeworkActivity.class);
            startActivity(intent);
        });
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        homeworkList = new ArrayList<>();
        adapter = new HomeworkAdapter(homeworkList);
        recyclerView.setAdapter(adapter);

        Ion.with(this)
                .load(UtilityClass.BASE_URL+"api/Homeworks/GetHomeworks")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (e != null) {
                            e.printStackTrace();
                            Toast.makeText(HomeworkActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        //Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
                        handleJsonArrayResponse(result);
                    }
                });

    }
    public void showDatePickerDialogLastDateFilter(View v) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // Set the selected date on the EditText
                        String selectedDate = day + "/" + (month + 1) + "/" + year;
                        Toast.makeText(HomeworkActivity.this, selectedDate.toString(), Toast.LENGTH_SHORT).show();
                        //homework_last_date.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }
    private void handleJsonArrayResponse(JsonArray jsonArray) {
        for (JsonElement jsonElement : jsonArray) {
            Homework homework = new Gson().fromJson(jsonElement, Homework.class);
            //Toast.makeText(MainActivity.this, jsonElement.toString(), Toast.LENGTH_SHORT).show();
            homeworkList.add(homework);
        }
        Toast.makeText(HomeworkActivity.this, homeworkList.get(1).toString(), Toast.LENGTH_SHORT).show();

        adapter.notifyDataSetChanged();
    }


}

