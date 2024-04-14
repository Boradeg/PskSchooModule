package com.example.pskschoolhomeworknotiholidaymodule.Holiday;

import com.google.gson.annotations.SerializedName;

public class Holiday2 {
    @SerializedName("OnDate")
    private String onDate;

    @SerializedName("Note")
    private String note;

    // Constructor
    public Holiday2(String onDate, String note) {
        this.onDate = onDate;
        this.note = note;
    }

    // Getters and setters (optional, but recommended)
    public String getOnDate() {
        return onDate;
    }

    public void setOnDate(String onDate) {
        this.onDate = onDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
