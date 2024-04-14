package com.example.pskschoolhomeworknotiholidaymodule.Holiday;


public class HoildayDataClass {
    private String title;
    private String description;
    private String userName;
    private String date;

    // Constructor
    public HoildayDataClass(String title, String description, String userName, String date) {
        this.title = title;
        this.description = description;
        this.userName = userName;
        this.date = date;
    }

    // Setter methods
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUserName() {
        return userName;
    }

    public String getDate() {
        return date;
    }
}


