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
import edu.csumb.gradetracker.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@RunWith(AndroidJUnit4.class)

public class CourseTest {

    private static TrackerDao trackerDao;
    private static TrackerRoom db;
    private static Course course;
    private static User user;

    //Creates a database in memory with the context of the database on file
    @Before
    public void createDb(){
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, TrackerRoom.class).build();
        trackerDao = db.getTrackerRoom(context).dao();
    }


    @Test
    public void insertCourse(){
        Course course = new Course("jerry", "dr. c", "cst438", "easy", "tuesday", "wednesday");
        trackerDao.addNewCourse(course);
        assertEquals("jerry", course.getUserName());
    }
    //todo: fix this case failure
    @Test
    public void deleteCourse(){
        Course course = new Course("jerry", "dr. c", "cst438", "easy", "tuesday", "wednesday");
        trackerDao.addNewCourse(course);
        trackerDao.deleteCourse(course);
        assertNull(course);
    }

    @Test
    public void getUserName() {
        Course course = new Course("jerry", "dr. c", "cst438", "easy", "tuesday", "wednesday");
        assertEquals("jerry", course.getUserName());
    }

    @Test
    public void setUserName() {
        Course c = new Course();
        c.setUserName("jerrizzlebizzle");
        assertEquals("jerrizzlebizzle", c.getUserName());
    }

    @Test
    public void getInstructor() {
        Course course = new Course("jerry", "dr. c", "cst438", "easy", "tuesday", "wednesday");
        assertEquals("dr. c", course.getInstructor());
    }

    @Test
    public void setInstructor() {
        Course c = new Course();
        c.setInstructor("rezz");
        assertEquals("rezz", c.getInstructor());
    }

    @Test
    public void getTitle() {
        Course course = new Course("jerry", "dr. c", "cst438", "easy", "tuesday", "wednesday");
        assertEquals("cst438", course.getTitle());
    }





    //TODO getcourses for user
    @Test
    public void getCoursesForUser() {
        user = new User("username", "password");
        course = new Course("username", "Dr. C", "CST438", "easy peasy lemon squeezy", "Tuesday", "Wednesday");
        trackerDao.addNewCourse(course);
        assertEquals("CST438", course.getTitle());
    }

    @Test
    public void setTitle() {
        Course c = new Course();
        c.setTitle("cst");
        assertEquals("cst", c.getTitle());
    }

    @Test
    public void getDescription() {
        Course course = new Course("jerry", "dr. c", "cst438", "easy", "tuesday", "wednesday");
        assertEquals("easy", course.getDescription());
    }


    @Test
    public void setDescription() {
        Course c = new Course();
        c.setDescription("easy");
        assertEquals("easy", c.getDescription());
    }

    @Test
    public void getStartDate(){
        Course course = new Course("jerry", "dr. c", "cst438", "easy", "tuesday", "wednesday");
        assertEquals("tuesday", course.getStartDate());
    }
    @Test
    public void setStartDate(){
        Course c = new Course();
        c.setStartDate("tuesday");
        assertEquals("tuesday", c.getStartDate());
    }
    @Test
    public void getEndDate (){
        Course course = new Course("jerry", "dr. c", "cst438", "easy", "tuesday", "wednesday");
        assertEquals("wednesday", course.getEndDate());
    }
    @Test
    public void setEndDate (){
        Course c = new Course();
        c.setEndDate("tuesday");
        assertEquals("tuesday", c.getEndDate());
    }
    @Test
    public void getId(){
        Course c = new Course();
        c.setId(9);
        assertEquals(9, c.getId());
    }
    @Test
    public void setId(){
        Course c = new Course();
        c.setId(3);
        assertEquals(3, c.getId());
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }


}
