package edu.csumb.gradetracker.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Assignment {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "Due Date")
    private String dueDate;

    @ColumnInfo(name = "Earned Score")
    private long earnedScore;

    @ColumnInfo(name = "Max Score")
    private long maxScore;

    @Ignore
    public Assignment(String dueDate, long earnedScore, long maxScore){
        this.dueDate = dueDate;
        this.earnedScore = earnedScore;
        this.maxScore = maxScore;
    }

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

    public long getEarnedScore() {
        return earnedScore;
    }

    public void setEarnedScore(long earnedScore) {
        this.earnedScore = earnedScore;
    }

    public long getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(long maxScore) {
        this.maxScore = maxScore;
    }
}
