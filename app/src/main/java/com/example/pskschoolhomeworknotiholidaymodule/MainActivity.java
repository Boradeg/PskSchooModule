package com.example.pskschoolhomeworknotiholidaymodule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pskschoolhomeworknotiholidaymodule.Holiday.GetHolidayActivity;
import com.example.pskschoolhomeworknotiholidaymodule.Homework.HomeworkActivity;
import com.example.pskschoolhomeworknotiholidaymodule.NoticeBoard.NoticeBoardActivity;
import com.example.pskschoolhomeworknotiholidaymodule.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.holiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GetHolidayActivity.class));
            }
        });
        binding.notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NoticeBoardActivity.class));
            }
        });
        binding.Homework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomeworkActivity.class));
            }
        });

    }
}