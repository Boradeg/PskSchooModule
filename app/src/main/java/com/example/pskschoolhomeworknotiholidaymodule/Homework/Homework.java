package com.example.pskschoolhomeworknotiholidaymodule.Homework;

import com.example.pskschoolhomeworknotiholidaymodule.UtilityClass;
import com.koushikdutta.async.Util;

public class Homework {
    private int HomeworkId;
    private int TeacherAppUserId;
    private String TeacherFullName;
    private int StandardId;
    private String StandardName;
    private int DivisionId;
    private String DivisionName;
    private int SubjectId;
    private String SubjectName;
    private String Note;
    private String FileName;
    private String CompletionOn;
    private String CreatedOn;
    private String ModifiedOn;

    // Generate setter and getter methods for each field
    public int getHomeworkId() {
        return HomeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.HomeworkId = homeworkId;
    }

    public int getTeacherAppUserId() {
        return TeacherAppUserId;
    }

    public void setTeacherAppUserId(int teacherAppUserId) {
        this.TeacherAppUserId = teacherAppUserId;
    }

    public String getTeacherFullName() {
        return TeacherFullName;
    }

    public void setTeacherFullName(String teacherFullName) {
        this.TeacherFullName = teacherFullName;
    }

    public int getStandardId() {
        return StandardId;
    }

    public void setStandardId(int standardId) {
        this.StandardId = standardId;
    }

    public String getStandardName() {
        return StandardName;
    }

    public void setStandardName(String standardName) {
        this.StandardName = standardName;
    }

    public int getDivisionId() {
        return DivisionId;
    }

    public void setDivisionId(int divisionId) {
        this.DivisionId = divisionId;
    }

    public String getDivisionName() {
        return DivisionName;
    }

    public void setDivisionName(String divisionName) {
        this.DivisionName = divisionName;
    }

    public int getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(int subjectId) {
        this.SubjectId = subjectId;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        this.SubjectName = subjectName;
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

    public String getCompletionOn() {
        return CompletionOn;
    }

    public void setCompletionOn(String completionOn) {
        this.CompletionOn = completionOn;
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
