package com.example.pskschoolhomeworknotiholidaymodule.Holiday;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pskschoolhomeworknotiholidaymodule.R;
import com.example.pskschoolhomeworknotiholidaymodule.databinding.ActivityGetHolidayBinding;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public class GetHolidayActivity extends AppCompatActivity {

    private List<Holiday> holidayList;
    private HolidayAdapter adapter;
    private RecyclerView recyclerView;
    private ActivityGetHolidayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityGetHolidayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.addHolidayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetHolidayActivity.this,CreateHolidayActivity.class));
            }
        });


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        holidayList = new ArrayList<>();
        adapter = new HolidayAdapter(holidayList);
        recyclerView.setAdapter(adapter);

        fetchHolidayData();
    }

    private void fetchHolidayData() {
        Ion.with(this)
                .load("https://www.pskeducation.co.in/api/Holidays/GetHolidays")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (e != null) {
                            e.printStackTrace();
                            Toast.makeText(GetHolidayActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        handleJsonArrayResponse(result);
                    }
                });
    }

    private void handleJsonArrayResponse(JsonArray jsonArray) {
        for (JsonElement jsonElement : jsonArray) {
            Holiday holiday = new Gson().fromJson(jsonElement, Holiday.class);
            holidayList.add(holiday);
        }
        adapter.notifyDataSetChanged();
    }
}
