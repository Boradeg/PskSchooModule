package com.example.pskschoolhomeworknotiholidaymodule.Holiday;


import com.example.pskschoolhomeworknotiholidaymodule.UtilityClass;
import com.google.gson.annotations.SerializedName;

public class Holiday {
    private int HolidayId;
    private String OnDate;
    private String Note;
    private String CreatedOn;
    private String ModifiedOn;

    public int getHolidayId() {
        return HolidayId;
    }

    public void setHolidayId(int holidayId) {
        HolidayId = holidayId;
    }

    public String getOnDate() {

        return OnDate;
    }

    public void setOnDate(String onDate) {
        OnDate = onDate;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }

    public String getModifiedOn() {
        return ModifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        ModifiedOn = modifiedOn;
    }
}

