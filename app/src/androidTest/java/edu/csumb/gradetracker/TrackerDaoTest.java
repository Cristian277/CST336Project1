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
import java.util.List;

import edu.csumb.gradetracker.model.Assignment;
import edu.csumb.gradetracker.model.Course;
import edu.csumb.gradetracker.model.TrackerDao;
import edu.csumb.gradetracker.model.TrackerRoom;
import edu.csumb.gradetracker.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class TrackerDaoTest {

    private static TrackerDao trackerDao;
    private static TrackerRoom db;
    private static User user;
    private static Course course;
    private static Assignment assignment;
    private static List<User> users;
    private static List<Course> courses;
    private static List<Assignment> assignments;

    //Creates a database in memory with the context of the database on file
    @Before
    public void createDb(){
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, TrackerRoom.class).build();
        trackerDao = db.getTrackerRoom(context).dao();
    }

    @Test
    public void addUser(){
        user = new User("username", "password");
        trackerDao.addUser(user);
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        users = trackerDao.getAllUsers();
        assertEquals(1, users.size());
        trackerDao.deleteUser(user);
        trackerDao.updateUser(user);
        users.remove(0);
    }

    //Fixed: Jon
    @Test
    public void addNewAssignment(){
        assignment = new Assignment("CST438", "App", "Tuesday",
                344.0, 44.0);
        trackerDao.addNewAssignment(assignment);
        assertEquals("App", assignment.getAssignmentName());
        assignments = trackerDao.getAllAssignments();
        assertEquals(1, assignments.size());
        trackerDao.deleteAssignment(assignment);
        assignments.remove(0);
    }

    //Fixed: Jon
    @Test
    public void addNewCourse(){
        course = new Course("jerry", "dr. c", "cst438", "easy", "tuesday", "wednesday");
        trackerDao.addNewCourse(course);
        assertEquals("jerry", course.getUserName());
        courses = trackerDao.getAllCourses();
        assertEquals(1, courses.size());
        trackerDao.deleteCourse(course);
        courses.remove(0);
    }


    @Test
    public void getCoursesForUser() {
        course = new Course("Jerry", "Dr. C", "CST438", "lemon squeezy", "Tuesday", "Wednesday");
        trackerDao.addNewCourse(course);
        courses = trackerDao.getCoursesForUser("Jerry");
        assertEquals(1, courses.size());
        trackerDao.deleteCourse(course);
        courses.remove(0);
    }


    @Test
    public void getAssignmentsForCourse(){
        assignment = new Assignment("CST438", "App", "Tuesday", 344.0, 44.0);
        trackerDao.addNewAssignment(assignment);
        assignments = trackerDao.getAssignmentsForCourse("CST438");
        assertEquals(1, assignments.size());
        trackerDao.deleteAssignment(assignment);
        assignments.remove(0);
    }

    @Test
    public void getAllUsers(){
        user = new User("username", "password");
        User testUser2 = new User("username", "password");
        trackerDao.addUser(user);
        trackerDao.addUser(testUser2);
        users = trackerDao.getAllUsers();
        assertEquals(2, users.size());
        trackerDao.deleteUser(user);
        trackerDao.deleteUser(testUser2);
        users.remove(1);
        users.remove(0);
    }

    @Test
    public void getAllCourses() {
        course = new Course("Jerry", "Dr. C", "CST438", "lemon squeezy", "Tuesday", "Wednesday");
        Course courseF = new Course("Jerry", "Dr. C", "CST438", "lemon squeezy", "Tuesday", "Wednesday");
        trackerDao.addNewCourse(course);
        trackerDao.addNewCourse(courseF);
        courses = trackerDao.getAllCourses();
        assertEquals(2, courses.size());
        trackerDao.deleteCourse(course);
        trackerDao.deleteCourse(courseF);
        courses.remove(1);
        courses.remove(0);
    }

    @Test
    public void getAllAssignments(){
        assignment = new Assignment("CST438", "App", "Tuesday", 344.0, 44.0);
        Assignment assignment2 = new Assignment("CST438", "App2", "Wednesday", 4000.0, 99.0);
        trackerDao.addNewAssignment(assignment);
        assignments = trackerDao.getAllAssignments();
        assertEquals(2, assignments.size());
        trackerDao.deleteAssignment(assignment);
        trackerDao.deleteAssignment(assignment2);
        assignments.remove(1);
        assignments.remove(0);
    }

    @Test
    public void deleteUser(){
        user = new User("testUsername","testPassword" );
        trackerDao.addUser(user);
        assertEquals("testUsername", user.getUsername());
        users = trackerDao.getAllUsers();
        trackerDao.deleteUser(user);
        assertEquals("0", users.size());
        users.remove(0);
    }


    //Fixed: Jon
    @Test
    public void deleteCourse(){
        course = new Course("jerry", "dr. c", "cst438", "easy", "tuesday", "wednesday");
        trackerDao.addNewCourse(course);
        trackerDao.deleteCourse(course);
        courses = trackerDao.getAllCourses();
        assertEquals(0, courses.size());
        courses.remove(0);
    }


    //Fixed: Jon
    @Test
    public void deleteAssignment(){
        assignment = new Assignment();
        trackerDao.addNewAssignment(assignment);
        trackerDao.deleteAssignment(assignment);
        assignments = trackerDao.getAllAssignments();
        assertEquals(0, assignments.size());
        assignments.remove(0);
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }



}