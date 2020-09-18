package edu.csumb.gradetracker.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Assignment {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "dueDate")
    private String dueDate;

    @ColumnInfo(name = "earnedScore")
    private Double earnedScore;

    @ColumnInfo(name = "maxScore")
    private Double maxScore;

    @ColumnInfo(name = "assignmentName")
    private String assignmentName;

    @ColumnInfo(name = "courseTitle")
    private String courseTitle;

    public Assignment() {}

    @Ignore
    public Assignment(String courseTitle, String assignmentName, String dueDate, Double earnedScore, Double maxScore){
        this.courseTitle=courseTitle;
        this.assignmentName = assignmentName;
        this.courseTitle = courseTitle;
        this.dueDate = dueDate;
        this.earnedScore = earnedScore;
        this.maxScore = maxScore;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getCourseTitle() { return courseTitle; }

    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Double getEarnedScore() {
        return earnedScore;
    }

    public void setEarnedScore(Double earnedScore) {
        this.earnedScore = earnedScore;
    }

    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    @Override
    public String toString() {
        return "Title: " + assignmentName + "\n" + "Due Date:  "+ dueDate + "\n" + "Earned Score: "+
                earnedScore + "\n" + "Max Score: " + maxScore + "\n" + "Grade: " + String.format("%.2f", (earnedScore/maxScore)*100) + "%" + "\n";
    }
}
