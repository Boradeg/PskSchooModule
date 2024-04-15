package com.example.pskschoolhomeworknotiholidaymodule.Holiday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.pskschoolhomeworknotiholidaymodule.R;
import com.example.pskschoolhomeworknotiholidaymodule.UtilityClass;
import com.example.pskschoolhomeworknotiholidaymodule.databinding.ActivityCreateHolidayBinding;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class CreateHolidayActivity extends AppCompatActivity {

    private TextInputEditText titleEditText;
    private AppCompatButton createButton;
    private ActivityCreateHolidayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCreateHolidayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        titleEditText = findViewById(R.id.title_name);
      //  descEditText = findViewById(R.id.notice_desc);
        //createdByEditText = findViewById(R.id.notice_created_by);
        //dateEditText = findViewById(R.id.holiday_date);
        createButton = findViewById(R.id.create_holiday);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get data from TextInputEditText fields
                String title = titleEditText.getText().toString().trim();
               // String desc = descEditText.getText().toString().trim();
               // String createdBy = createdByEditText.getText().toString().trim();
                String date = "2024-04-13T00:00:00";



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
                                Toast.makeText(CreateHolidayActivity.this, "Holiday created successfully!", Toast.LENGTH_SHORT).show();

                                // Clear TextInputEditText fields
                                titleEditText.setText("");

                            }
                        });
            }
        });
    }

    public void showDatePickerDialogLastDateCreateHoliday(View view) {
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
                        binding.holidayDate.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }
}