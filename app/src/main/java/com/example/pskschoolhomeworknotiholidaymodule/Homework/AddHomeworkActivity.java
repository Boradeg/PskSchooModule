package com.example.pskschoolhomeworknotiholidaymodule.Homework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.pskschoolhomeworknotiholidaymodule.NoticeBoard.AddNoticeActivity;
import com.example.pskschoolhomeworknotiholidaymodule.R;
import com.example.pskschoolhomeworknotiholidaymodule.UtilityClass;
import com.example.pskschoolhomeworknotiholidaymodule.databinding.ActivityAddHomeworkBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddHomeworkActivity extends AppCompatActivity {

    private StandardData selectedStandard;
    private String selectedDivision;

    private ActivityAddHomeworkBinding binding;
    private AutoCompleteTextView autoCompleteTextViewStandard,autoCompleteTextViewSubjects;
    private AutoCompleteTextView autoCompleteTextViewDivision;
    private List<StandardData> standardList;
    private ArrayAdapter<String> divisionAdapter;

    // Variables to store selected division and subject IDs
    private int selectedDivisionId;
    private int selectedSubjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddHomeworkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.homeworkLastDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilityClass.showDatePickerDialog2(AddHomeworkActivity.this,binding.homeworkLastDate);
            }
        });

        autoCompleteTextViewStandard = binding.tvStandard;
        autoCompleteTextViewDivision = binding.tvDivision;
        autoCompleteTextViewSubjects = binding.tvSubject;
        standardList = new ArrayList<>();
        fetchStandardData();
        Toast.makeText(this, selectedDivisionId+" "+selectedSubjectId, Toast.LENGTH_SHORT).show();
        // Initially disable the division AutoCompleteTextView
        autoCompleteTextViewDivision.setEnabled(false);
        autoCompleteTextViewSubjects.setEnabled(false);

        binding.addHomeworkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createHomework();
                Toast.makeText(AddHomeworkActivity.this, selectedDivisionId+" "+selectedSubjectId, Toast.LENGTH_SHORT).show();

            }
        });



    }


    public void createHomework() {
        String note = binding.titleName.getText().toString().trim();
        String date2 = binding.homeworkLastDate.getText().toString().trim();

        // Send POST request using Ion library
        Ion.with(this)
                .load("POST", UtilityClass.BASE_URL+"api/Homeworks/CreateHomeworks")
                .setMultipartParameter("TeacherAppUserId", "1")
                .setMultipartParameter("DivisionId",String.valueOf(selectedDivisionId))
                .setMultipartParameter("SubjectId", String.valueOf(selectedSubjectId))
                .setMultipartParameter("Note", note)
                .setMultipartParameter("File", "demo.pdf")
                .setMultipartParameter("CompletionOn",date2)
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
                    }
                });
    }
    private void fetchStandardData() {
        autoCompleteTextViewSubjects.setText(""); // Clear text
        //autoCompleteTextViewDivision.setHint("Select Division"); // Set default hint
        autoCompleteTextViewDivision.setAdapter(null); // Clear adapter

        Ion.with(this)
                .load(UtilityClass.BASE_URL+"api/Academics/GetStandards")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (e != null) {
                            e.printStackTrace();
                            Toast.makeText(AddHomeworkActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        handleJsonArrayResponse(result);
                    }
                });
    }

    private void handleJsonArrayResponse(JsonArray jsonArray) {
        try {
            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                int standardId = jsonObject.get("StandardId").getAsInt();
                String standardName = jsonObject.get("StandardName").getAsString();
                standardList.add(new StandardData(standardId, standardName));
            }

            ArrayAdapter<StandardData> adapter = new ArrayAdapter<>(this,R.layout.drop_down_layout2, standardList);
            autoCompleteTextViewStandard.setAdapter(adapter);

            autoCompleteTextViewStandard.setOnItemClickListener((parent, view, position, id) -> {
                StandardData selectedStandard = (StandardData) parent.getItemAtPosition(position);
                Toast.makeText(AddHomeworkActivity.this, "Selected Standard: " + selectedStandard.getName() + ", ID: " + selectedStandard.getId(), Toast.LENGTH_SHORT).show();
                fetchDivisionData(selectedStandard.getId());

                // Enable the division AutoCompleteTextView
                autoCompleteTextViewDivision.setEnabled(true);
            });

        } catch (JsonParseException e) {
            e.printStackTrace();
            Toast.makeText(AddHomeworkActivity.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchDivisionData(int standardId) {
        // Reset selected division and clear the adapter
        selectedDivision = null;
        autoCompleteTextViewDivision.setText(""); // Clear text
        //autoCompleteTextViewDivision.setHint("Select Division"); // Set default hint
        autoCompleteTextViewDivision.setAdapter(null); // Clear adapter

        Ion.with(this)
                .load(UtilityClass.BASE_URL+"api/Academics/GetDivisions?StandardId=" + standardId)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (e != null) {
                            e.printStackTrace();
                            Toast.makeText(AddHomeworkActivity.this, "Error fetching division data", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        handleDivisionJsonArrayResponse(result);
                    }
                });
    }


    private void handleDivisionJsonArrayResponse(JsonArray jsonArray) {
        List<Division> divisionList = new ArrayList<>();
        try {
            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                int divisionId = jsonObject.get("DivisionId").getAsInt();
                int standardId = jsonObject.get("StandardId").getAsInt();
                String divisionName = jsonObject.get("DivisionName").getAsString();
                divisionList.add(new Division(divisionId, standardId, divisionName));
            }

            // Log the size of the division list
            Log.d("DivisionListSize", "Division list size: " + divisionList.size());

            ArrayAdapter<Division> adapter = new ArrayAdapter<>(this,R.layout.drop_down_layout2, divisionList);
            autoCompleteTextViewDivision.setAdapter(adapter);

            autoCompleteTextViewDivision.setOnItemClickListener((parent, view, position, id) -> {
                Division selectedDivisionItem = (Division) parent.getItemAtPosition(position);
                int divisionId = selectedDivisionItem.getId();
                selectedDivisionId=selectedDivisionItem.getId();
                fetchSubjects(selectedDivisionItem.standardId);
                autoCompleteTextViewSubjects.setEnabled(true);

            });

        } catch (JsonParseException e) {
            e.printStackTrace();
            Toast.makeText(AddHomeworkActivity.this, "Error parsing division JSON", Toast.LENGTH_SHORT).show();
        }
    }
    private static class Division {
        private int id;
        private int standardId;
        private String name;

        public Division(int id, int standardId, String name) {
            this.id = id;
            this.standardId = standardId;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public int getStandardId() {
            return standardId;
        }

        public String getName() {
            return name;
        }

        @NonNull
        @Override
        public String toString() {
            return name; // Display the name in the AutoCompleteTextView
        }
    }

    private static class StandardData {
        private int id;
        private String name;

        public StandardData(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @NonNull
        @Override
        public String toString() {
            return name; // Display the name in the AutoCompleteTextView
        }
    }
    private void fetchSubjects(int standardId) {

        // Reset selected division and clear the adapter
        autoCompleteTextViewSubjects.setText(""); // Clear text
        autoCompleteTextViewSubjects.setAdapter(null); // Clear adapter
        autoCompleteTextViewSubjects.setEnabled(true);

        Ion.with(this)
                .load(UtilityClass.BASE_URL + "api/Academics/GetSubjects?StandardId=" +standardId)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (e != null) {
                            e.printStackTrace();
                            Toast.makeText(AddHomeworkActivity.this, "Error fetching subjects data", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        handleSubjectsJsonArrayResponse(result);
                    }
                });
    }

    private void handleSubjectsJsonArrayResponse(JsonArray jsonArray) {
        List<Subject> subjects = new ArrayList<>();
        try {
            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                int subjectId = jsonObject.get("SubjectId").getAsInt();
                int standardId = jsonObject.get("StandardId").getAsInt();
                String subjectName = jsonObject.get("SubjectName").getAsString();
                subjects.add(new Subject(subjectId, standardId, subjectName));
            }

            ArrayAdapter<Subject> adapter = new ArrayAdapter<>(this, R.layout.drop_down_layout2, subjects);
            autoCompleteTextViewSubjects.setAdapter(adapter);

            autoCompleteTextViewSubjects.setOnItemClickListener((parent, view, position, id) -> {
                Subject selectedSubject = (Subject) parent.getItemAtPosition(position);
                // Do something with the selected subject
                selectedSubjectId=selectedSubject.getId();
                Toast.makeText(AddHomeworkActivity.this, "Selected Subject: " + selectedSubject.getName(), Toast.LENGTH_SHORT).show();
            });


        } catch (JsonParseException e) {
            e.printStackTrace();
            Toast.makeText(AddHomeworkActivity.this, "Error parsing subjects JSON", Toast.LENGTH_SHORT).show();
        }
    }

    private static class Subject {
        private int id;
        private int standardId;
        private String name;

        public Subject(int id, int standardId, String name) {
            this.id = id;
            this.standardId = standardId;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public int getStandardId() {
            return standardId;
        }

        public String getName() {
            return name;
        }

        @NonNull
        @Override
        public String toString() {
            return name; // Display the name in the AutoCompleteTextView
        }
    }

}