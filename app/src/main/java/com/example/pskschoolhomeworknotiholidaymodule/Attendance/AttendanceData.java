package com.example.pskschoolhomeworknotiholidaymodule.Attendance;

import com.example.pskschoolhomeworknotiholidaymodule.UtilityClass;

public class AttendanceData {
    private int attendanceSheetId;
    private int teacherAppUserId;
    private String teacherFullName;
    private int standardId;
    private String standardName;
    private int divisionId;
    private String divisionName;
    private String onDate;
    private int noOfNotAvailables;
    private int noOfPresents;
    private int noOfAbsents;
    private String createdOn;
    private String modifiedOn;

    // Constructor
    public AttendanceData(int attendanceSheetId, int teacherAppUserId, String teacherFullName,
                          int standardId, String standardName, int divisionId, String divisionName,
                          String onDate, int noOfNotAvailables, int noOfPresents, int noOfAbsents,
                          String createdOn, String modifiedOn) {
        this.attendanceSheetId = attendanceSheetId;
        this.teacherAppUserId = teacherAppUserId;
        this.teacherFullName = teacherFullName;
        this.standardId = standardId;
        this.standardName = standardName;
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.onDate = onDate;
        this.noOfNotAvailables = noOfNotAvailables;
        this.noOfPresents = noOfPresents;
        this.noOfAbsents = noOfAbsents;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    // Getters and setters
    public int getAttendanceSheetId() {
        return attendanceSheetId;
    }

    public void setAttendanceSheetId(int attendanceSheetId) {
        this.attendanceSheetId = attendanceSheetId;
    }

    public int getTeacherAppUserId() {
        return teacherAppUserId;
    }

    public void setTeacherAppUserId(int teacherAppUserId) {
        this.teacherAppUserId = teacherAppUserId;
    }

    public String getTeacherFullName() {
        return teacherFullName;
    }

    public void setTeacherFullName(String teacherFullName) {
        this.teacherFullName = teacherFullName;
    }

    public int getStandardId() {
        return standardId;
    }

    public void setStandardId(int standardId) {
        this.standardId = standardId;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getOnDate() {
        return onDate;
    }

    public void setOnDate(String onDate) {
        this.onDate = onDate;
    }

    public int getNoOfNotAvailables() {
        return noOfNotAvailables;
    }

    public void setNoOfNotAvailables(int noOfNotAvailables) {
        this.noOfNotAvailables = noOfNotAvailables;
    }

    public int getNoOfPresents() {
        return noOfPresents;
    }

    public void setNoOfPresents(int noOfPresents) {
        this.noOfPresents = noOfPresents;
    }

    public int getNoOfAbsents() {
        return noOfAbsents;
    }

    public void setNoOfAbsents(int noOfAbsents) {
        this.noOfAbsents = noOfAbsents;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
