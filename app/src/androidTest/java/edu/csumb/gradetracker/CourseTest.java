package edu.csumb.gradetracker;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import edu.csumb.gradetracker.model.Course;
import edu.csumb.gradetracker.model.TrackerDao;
import edu.csumb.gradetracker.model.TrackerRoom;

/*
    1. Insert and Test a Course in the Database
        a. Test to see if user was entered (assertEquals)
    2. Delete the Course from the database
        a. Delete and test if the Course was deleted(Cycle DB and assertNull)
    3.
 */

@RunWith(AndroidJUnit4.class)
public class CourseTest {
    private static TrackerDao trackerDao;
    private static TrackerRoom db;
    private static Course course;

    //Creates a database in memory with the context of the database on file
    @Before
    public void createDb(){
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, TrackerRoom.class).build();
        trackerDao = db.getTrackerRoom(context).dao();
    }


    @Test
    public void insertCourse(){
        course = new Course();
    }

    @Test
    public void deleteCourse(){

    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }


}
