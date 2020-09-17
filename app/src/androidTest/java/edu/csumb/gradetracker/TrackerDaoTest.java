package edu.csumb.gradetracker;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import edu.csumb.gradetracker.model.Assignment;
import edu.csumb.gradetracker.model.Course;
import edu.csumb.gradetracker.model.TrackerDao;
import edu.csumb.gradetracker.model.TrackerRoom;
import edu.csumb.gradetracker.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class TrackerDaoTest {

    private static TrackerDao trackerDao;
    private static TrackerRoom db;

    //Creates a database in memory with the context of the database on file
    @Before
    public void createDb(){
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, TrackerRoom.class).build();
        trackerDao = db.getTrackerRoom(context).dao();
    }


    @Test
    public void getCoursesForUser() {
        Course courseE = new Course("Jerry", "Dr. C", "CST438", "lemon squeezy", "Tuesday", "Wednesday");
        trackerDao.addNewCourse(courseE);
        List<Course> testCourse = trackerDao.getCoursesForUser("Jerry");

        assertEquals(1, testCourse.size());
    }

    @Test
    public void addUser(){

        User testUser = new User("username", "password");
        trackerDao.addUser(testUser);
        assertEquals("username", testUser.getUsername());
    }

    @Test
    public void addNewAssignment(){
        Assignment assignment = new Assignment("CST438", "App", "Tuesday", 344, 44);
        trackerDao.addNewAssignment(assignment);
        assertEquals("App", assignment.getAssignmentName());
    }

    @Test
    public void getAssignmentsForCourse(){
        Assignment assignment = new Assignment("CST438", "App", "Tuesday", 344, 44);
        trackerDao.addNewAssignment(assignment);
        List<Assignment> testAssignment = trackerDao.getAssignmentsForCourse("CST438");

        assertEquals(1, testAssignment.size());
    }

    @Test
    public void getAllUsers(){
        User testUser = new User("username", "password");
        trackerDao.addUser(testUser);
        List<User> user = trackerDao.getAllUsers();

        assertEquals(1, user.size());
    }
    //todo: fix this case failure
    @Test
    public void deleteUser(){
        User testUser = new User("username", "password");
        trackerDao.addUser(testUser);
        trackerDao.deleteUser(testUser);
        assertNotEquals("username", testUser.getUsername());
    }

    @Test
    public void getAllCourses() {
        Course courseE = new Course("Jerry", "Dr. C", "CST438", "lemon squeezy", "Tuesday", "Wednesday");
        trackerDao.addNewCourse(courseE);
        List<Course> testCourse = trackerDao.getAllCourses();

        assertEquals(1, testCourse.size());
    }

    @Test
    public void addNewCourse(){
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
    public void getAllAssignments(){
        Assignment assignment = new Assignment("CST438", "App", "Tuesday", 344, 44);
        trackerDao.addNewAssignment(assignment);
        List<Assignment> testAssignment = trackerDao.getAllAssignments();
        assertEquals(1, testAssignment.size());
    }

    //todo: fix this case failure
    @Test
    public void deleteAssignment(){
        Assignment assignment = new Assignment();
        trackerDao.addNewAssignment(assignment);
        trackerDao.deleteAssignment(assignment);
        assertNull(assignment);
    }



    @After
    public void closeDb() throws IOException {
        db.close();
    }



}