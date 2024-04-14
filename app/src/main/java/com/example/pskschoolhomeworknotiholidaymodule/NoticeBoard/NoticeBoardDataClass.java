package com.example.pskschoolhomeworknotiholidaymodule.NoticeBoard;

import com.example.pskschoolhomeworknotiholidaymodule.UtilityClass;

public class NoticeBoardDataClass {
    private int SchoolNotificationId;
    private String Title;
    private String Note;
    private String FileName;
    private String StartOn;
    private String EndOn;
    private String CreatedOn;
    private String ModifiedOn;

    // Generate setter and getter methods for each field
    public int getSchoolNotificationId() {
        return SchoolNotificationId;
    }

    public void setSchoolNotificationId(int schoolNotificationId) {
        this.SchoolNotificationId = schoolNotificationId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        this.Note = note;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        this.FileName = fileName;
    }

    public String getStartOn() {
        return StartOn;
    }

    public void setStartOn(String startOn) {
        this.StartOn = startOn;
    }

    public String getEndOn() {
        return EndOn;
    }

    public void setEndOn(String endOn) {
        this.EndOn = endOn;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        this.CreatedOn = createdOn;
    }

    public String getModifiedOn() {
       return UtilityClass.formatDate(ModifiedOn);

    }

    public void setModifiedOn(String modifiedOn) {
        this.ModifiedOn = modifiedOn;
    }
}

