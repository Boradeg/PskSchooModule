package com.example.pskschoolhomeworknotiholidaymodule.Attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.pskschoolhomeworknotiholidaymodule.Homework.Homework;
import com.example.pskschoolhomeworknotiholidaymodule.R;
import com.example.pskschoolhomeworknotiholidaymodule.UtilityClass;

import com.example.pskschoolhomeworknotiholidaymodule.databinding.ActivityGetAttendanceBinding;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetAttendanceActivity extends AppCompatActivity {
    private AttendanceAdapter adapter;
    private List<AttendanceData> attendanceList;
    private RecyclerView recyclerView;
    private ActivityGetAttendanceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityGetAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.e("GetAttendanceActivity","1");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        attendanceList = new ArrayList<>();
        adapter = new AttendanceAdapter(attendanceList);
        recyclerView.setAdapter(adapter);

        // Make API call and populate the list
        fetchData();
        binding.addHolidayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetAttendanceActivity.this,CreateAttendanceActivity.class));
            }
        });


    }
    private void fetchData() {
        String apiUrl = "http://23.19.117.195:8080/pskshikshallp_lsm_api/api/Attendances/GetAttendanceSheetsDetailsByTeacherAppUserId?TeacherAppUserId=1";

        Ion.with(this)
                .load(apiUrl)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (e != null) {
                            // Handle API call failure
                           // showToast("Error: " + e.getMessage());
                            return;
                        }

                        // Parse the JSON response
                        Gson gson = new Gson();
                        for (JsonElement jsonElement : result) {
                            JsonObject jsonObject = jsonElement.getAsJsonObject();
                            int attendanceSheetId = jsonObject.get("AttendanceSheetId").getAsInt();
                            int teacherAppUserId = jsonObject.get("TeacherAppUserId").getAsInt();
                            String teacherFullName = jsonObject.get("TeacherFullName").getAsString();
                            int standardId = jsonObject.get("StandardId").getAsInt();
                            String standardName = jsonObject.get("StandardName").getAsString();
                            int divisionId = jsonObject.get("DivisionId").getAsInt();
                            String divisionName = jsonObject.get("DivisionName").getAsString();
                            String onDate = jsonObject.get("OnDate").getAsString();
                            int noOfNotAvailables = jsonObject.get("NoOfNotAvailables").getAsInt();
                            int noOfPresents = jsonObject.get("NoOfPresents").getAsInt();
                            int noOfAbsents = jsonObject.get("NoOfAbsents").getAsInt();
                            String createdOn = jsonObject.get("CreatedOn").getAsString();
                            String modifiedOn = jsonObject.get("ModifiedOn").getAsString();

                            // Add the data to the list
                            attendanceList.add(new AttendanceData(attendanceSheetId, teacherAppUserId, teacherFullName,
                                    standardId, standardName, divisionId, divisionName, onDate, noOfNotAvailables,
                                    noOfPresents, noOfAbsents, createdOn, modifiedOn));
                        }

                        // Notify the adapter of the data change
                        adapter.notifyDataSetChanged();
                    }
                });
    }


}