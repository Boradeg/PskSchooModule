package com.example.pskschoolhomeworknotiholidaymodule.Homework;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.pskschoolhomeworknotiholidaymodule.NoticeBoard.AddNoticeActivity;
import com.example.pskschoolhomeworknotiholidaymodule.R;
import com.example.pskschoolhomeworknotiholidaymodule.databinding.ActivityAddHomeworkBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.Calendar;

public class AddHomeworkActivity extends AppCompatActivity {
    private ActivityAddHomeworkBinding binding;

    private TextInputEditText homework_assign_date;
    private TextInputEditText homework_last_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddHomeworkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
          binding.addHomeworkBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  createHomework();
              }
          });
        homework_assign_date = findViewById(R.id.homework_assign_date);
        homework_last_date = findViewById(R.id.homework_last_date);

        // Change the ID to match your TextInputEditText ID

    }
    // Method to show DatePickerDialog
    public void showDatePickerDialogAssignDate(View v) {
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
                        homework_assign_date.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }
    public void showDatePickerDialogLastDate(View v) {
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
                        homework_last_date.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }
    public void createHomework() {
        // Get data from the TextInputEditText fields
       // String title = binding.titleName.getText().toString().trim(); // Get title from EditText
        String note = binding.noticeDesc.getText().toString().trim(); // Get note from EditText
        String startOn = "2024-04-13T16:03:52.243"; // You can get startOn from a DatePicker or another EditText
        String endOn = "2024-04-13T16:03:52.243"; // You can get endOn from a DatePicker or another EditText

        if (note.isEmpty()) {
            // Display error message if any field is empty
            Toast.makeText(AddHomeworkActivity.this, "Title and Note are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Send POST request using Ion library
        Ion.with(this)
                .load("POST", "https://www.pskeducation.co.in/api/Homeworks/CreateHomeworks")
                .setMultipartParameter("TeacherAppUserId", "1")
                .setMultipartParameter("DivisionId", "1")
                .setMultipartParameter("SubjectId", "1")
                .setMultipartParameter("Note", note)
                .setMultipartParameter("File", "demo.pdf")
                .setMultipartParameter("CompletionOn", "2024-04-13 16:03:52.243")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e != null) {
                            // Error handling
                            e.printStackTrace();
                            return;
                        }
                        // Inside the onCompleted method
                        Toast.makeText(AddHomeworkActivity.this, "Response: " + result, Toast.LENGTH_SHORT).show();
                        Log.d("AddHomeworkActivity",result);
                        // Parse the response if needed
                        // Display success message
                        Toast.makeText(AddHomeworkActivity.this, "Notice created successfully!", Toast.LENGTH_SHORT).show();

                        // Clear TextInputEditText fields if needed
                        // titleEditText.setText("");
                        // noteEditText.setText("");
                        // startOnEditText.setText("");
                        // endOnEditText.setText("");
                    }
                });
    }
}