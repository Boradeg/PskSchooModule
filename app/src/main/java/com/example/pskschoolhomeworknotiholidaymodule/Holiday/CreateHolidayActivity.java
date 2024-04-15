package com.example.pskschoolhomeworknotiholidaymodule.Holiday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.pskschoolhomeworknotiholidaymodule.Homework.AddHomeworkActivity;
import com.example.pskschoolhomeworknotiholidaymodule.R;
import com.example.pskschoolhomeworknotiholidaymodule.UtilityClass;
import com.example.pskschoolhomeworknotiholidaymodule.databinding.ActivityCreateHolidayBinding;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Objects;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class CreateHolidayActivity extends AppCompatActivity {


    private ActivityCreateHolidayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCreateHolidayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.holidayDateAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilityClass.showDatePickerDialog2(CreateHolidayActivity.this,binding.holidayDateAdd);
                Toast.makeText(CreateHolidayActivity.this, binding.holidayDateAdd.getText(), Toast.LENGTH_SHORT).show();

            }
        });



        binding.createHoliday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get data from TextInputEditText fields
                String title = binding.holidayTitleNameAdd.getText().toString().trim();
               // String desc = descEditText.getText().toString().trim();
               // String createdBy = createdByEditText.getText().toString().trim();
               //String date = "2024-04-13";
                String date = binding.holidayDateAdd.getText().toString().trim();;



                // Create Holiday object
                Holiday2 holiday2 = new Holiday2(date, title);

                // Send POST request using Ion library
                Ion.with(CreateHolidayActivity.this)
                        .load("POST", UtilityClass.BASE_URL+"api/Holidays/CreateHolidays")
                        .setJsonPojoBody(holiday2)
                        .asString()
                        .setCallback(new FutureCallback<String>() {
                            @Override
                            public void onCompleted(Exception e, String result) {
                                if (e != null) {
                                    // Error handling
                                    e.printStackTrace();
                                    return;
                                }

                                // Parse the response if needed
                                // Display success message
                                Toast.makeText(CreateHolidayActivity.this, result, Toast.LENGTH_SHORT).show();

                                // Clear TextInputEditText fields
                                //titleEditText.setText("");

                            }
                        });
            }
        });
    }

}