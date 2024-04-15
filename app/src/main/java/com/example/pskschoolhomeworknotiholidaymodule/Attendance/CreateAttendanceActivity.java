package com.example.pskschoolhomeworknotiholidaymodule.Attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.AttachedSurfaceControl;
import android.view.View;
import android.widget.Toast;

import com.example.pskschoolhomeworknotiholidaymodule.Holiday.CreateHolidayActivity;
import com.example.pskschoolhomeworknotiholidaymodule.Holiday.Holiday2;
import com.example.pskschoolhomeworknotiholidaymodule.R;
import com.example.pskschoolhomeworknotiholidaymodule.UtilityClass;
import com.example.pskschoolhomeworknotiholidaymodule.databinding.ActivityCreateAttendanceBinding;
import com.example.pskschoolhomeworknotiholidaymodule.databinding.ActivityCreateHolidayBinding;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class CreateAttendanceActivity extends AppCompatActivity {
    private ActivityCreateAttendanceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding=ActivityCreateAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.createAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create JSON object for the request body
                String date = binding.attendanceDateAdd.getText().toString().trim();;
                JsonObject json = new JsonObject();
                json.addProperty("TeacherAppUserId", 1);
                json.addProperty("DivisionId", 1);
                json.addProperty("OnDate", date);

                // Make API call using Ion
                Ion.with(CreateAttendanceActivity.this)
                        .load("POST", UtilityClass.BASE_URL+"api/Attendances/CreateAttendanceSheet")
                        .setJsonObjectBody(json)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (e != null) {
                                    // Handle error
                                    e.printStackTrace();
                                    return;
                                }

                                // Handle response
                                Toast.makeText(CreateAttendanceActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
                                if (result != null) {
                                    // Parse response
                                    int attendanceSheetId = result.get("AttendanceSheetId").getAsInt();
                                    // Extract other data from the response as needed
                                }
                            }
                        });
            }
        });



        binding.attendanceDateAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilityClass.showDatePickerDialog2(CreateAttendanceActivity.this,binding.attendanceDateAdd);
                Toast.makeText(CreateAttendanceActivity.this, binding.attendanceDateAdd.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}