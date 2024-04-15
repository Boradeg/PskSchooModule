package com.example.pskschoolhomeworknotiholidaymodule.NoticeBoard;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pskschoolhomeworknotiholidaymodule.R;
import com.example.pskschoolhomeworknotiholidaymodule.UtilityClass;
import com.example.pskschoolhomeworknotiholidaymodule.databinding.ActivityAddNoticeBinding;
import com.example.pskschoolhomeworknotiholidaymodule.databinding.ActivityGetHolidayBinding;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class AddNoticeActivity extends AppCompatActivity {
    private ActivityAddNoticeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddNoticeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.createNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotice();
            }
        });

    }

    public void showDatePickerDialogLastDateCreateNotice(View v) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // Set the selected date on the EditText
                        String selectedDate = year + "-" + (month + 1) + "-" + day;
                        Toast.makeText(AddNoticeActivity.this, selectedDate, Toast.LENGTH_SHORT).show();
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    public void createNotice() {
        // Get data from the TextInputEditText fields
        String title = binding.noticeTitleName.getText().toString().trim(); // Get title from EditText
        String note = binding.noticeDesc.getText().toString().trim(); // Get note from EditText
        String startOn = "2024-04-13T16:03:52.243"; // You can get startOn from a DatePicker or another EditText
        String endOn = "2024-04-13T16:03:52.243"; // You can get endOn from a DatePicker or another EditText

        // Check if both title and note are not empty
        if (title.isEmpty() || note.isEmpty()) {
            // Display error message if any field is empty
            Toast.makeText(AddNoticeActivity.this, "Title and Note are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Send POST request using Ion library
        Ion.with(this)
                .load("POST", UtilityClass.BASE_URL+"api/SchoolNotifications/CreateSchoolNotification/")
                .setMultipartParameter("Title", title)
                .setMultipartParameter("Note", note)
                .setMultipartParameter("File", "Noye.pdf")
                .setMultipartParameter("StartOn", startOn)
                .setMultipartParameter("EndOn", endOn)
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
                        Toast.makeText(AddNoticeActivity.this, "Response: " + result, Toast.LENGTH_SHORT).show();
                        Log.d("AddNoticeActivity",result);
                        // Parse the response if needed
                        // Display success message
                        Toast.makeText(AddNoticeActivity.this, "Notice created successfully!", Toast.LENGTH_SHORT).show();

                        // Clear TextInputEditText fields if needed
                        // titleEditText.setText("");
                        // noteEditText.setText("");
                        // startOnEditText.setText("");
                        // endOnEditText.setText("");
                    }
                });
    }
}
