package com.example.pskschoolhomeworknotiholidaymodule;


import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
        import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
        import java.util.Locale;

public class UtilityClass {
    public static final String BASE_URL = "http://23.19.117.195:8080/pskshikshallp_lsm_api/";

    public static String formatDate(String dateTimeString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date date = inputFormat.parse(dateTimeString);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void showDatePickerDialog2(Context context, final TextInputEditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // Set the selected date on the EditText in "yyyy-MM-dd" format
                        String selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, day);

                        editText.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

}

