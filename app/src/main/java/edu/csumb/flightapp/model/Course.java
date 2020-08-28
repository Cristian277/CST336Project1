package edu.csumb.flightapp.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Course{
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String instructor;
    private String title;
    private String description;
    private String startDate;
    private String endDate;

    public Course() {}

    @Ignore
    public Course(String instructor, String title, String description, String startDate, String endDate) {
        this.instructor = instructor;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Instructor: " + instructor + "\n" + "Course Title:  "+ title + "\n" + "Description: "+
                description + "\n" + "Start Date: " + startDate + "\n"
                + "End Date: " + endDate + "\n";
    }

}
