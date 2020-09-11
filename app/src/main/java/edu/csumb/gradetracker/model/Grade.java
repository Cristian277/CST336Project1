package edu.csumb.gradetracker.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity

public class Grade {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "score")
    private int score;

    @ColumnInfo(name = "assignmentID")
    private int assignmentID;

    @ColumnInfo(name = "studentID")
    private int studentID;

    @ColumnInfo(name = "dateEarned")
    private String dateEarned;

    @ColumnInfo(name = "gradeID")
    private int gradeID;

    public Grade(){}

    @Ignore
    public Grade(int score, int assignmentID, int studentID, String dateEarned, int gradeID) {
        this.score = score;
        this.assignmentID = assignmentID;
        this.studentID = studentID;
        this.dateEarned = dateEarned;
        this.gradeID = gradeID;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getGradeID() {
        return gradeID;
    }

    public void setGradeID(int gradeID) {
        this.gradeID = gradeID;
    }

    public String getDateEarned() {
        return dateEarned;
    }

    public void setDateEarned(String dateEarned) {
        this.dateEarned = dateEarned;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", score= " + score +
                ", assignmentID = " + assignmentID +
                ", studentID = " + studentID +
                ", dateEarned = " + dateEarned + '\'' +
                ", gradeID = " + gradeID +
                '}';
    }
}//class
