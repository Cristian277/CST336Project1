package edu.csumb.gradetracker;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
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

    //Creates a database in memory with the context of the database on file

    @Before
    public void createDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                TrackerRoom.class)
                .allowMainThreadQueries()
                .build();
        trackerDao = db.dao();

    }

    @Test
    public void addUser(){
        List<User> users;
        User user = new User("username", "password");
        trackerDao.addUser(user);
        users = trackerDao.getAllUsers();
        assertEquals("username", users.get(0).getUsername());
        assertEquals("password", users.get(0).getPassword());
        assertEquals(1, users.size());
    }

    @Test
    public void addNewAssignment(){

        List<Assignment> assignments = new ArrayList<>();

        Assignment assignment = new Assignment("CST438", "App", "Tuesday",
                344.0, 44.0);
        trackerDao.addNewAssignment(assignment);
        assignments = trackerDao.getAllAssignments();
        assertEquals("App", assignments.get(0).getAssignmentName());
        assertEquals(1, assignments.size());
    }

    @Test
    public void addNewCourse(){
        List<Course> courses;
        Course course = new Course("jerry", "dr. c", "cst438", "easy", "tuesday", "wednesday");
        trackerDao.addNewCourse(course);
        courses = trackerDao.getAllCourses();
        assertEquals("cst438", courses.get(0).getTitle());
        assertEquals(1, courses.size());
    }

    @Test
    public void getCoursesForUser() {
        List<Course>courses;
        Course course = new Course("Jerry", "Dr. C", "CST438", "lemon squeezy", "Tuesday", "Wednesday");
        trackerDao.addNewCourse(course);
        courses = trackerDao.getCoursesForUser("Jerry");
        assertEquals(1, courses.size());
    }

    @Test
    public void getAssignmentsForCourse(){
        List<Assignment>assignments;
        Assignment assignment = new Assignment("CST438", "App", "Tuesday", 344.0, 44.0);
        trackerDao.addNewAssignment(assignment);
        assignments = trackerDao.getAssignmentsForCourse("CST438");
        assertEquals(1, assignments.size());
    }

    @Test
    public void getAllUsers(){
        List<User>users;
        User user = new User("username", "password");
        User testUser2 = new User("username", "password");
        trackerDao.addUser(user);
        trackerDao.addUser(testUser2);
        users = trackerDao.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    public void getAllCourses() {
        List<Course>courses;
        Course course = new Course("Jerry", "Dr. C", "CST438", "lemon squeezy", "Tuesday", "Wednesday");
        Course courseF = new Course("Jerry", "Dr. C", "CST438", "lemon squeezy", "Tuesday", "Wednesday");
        trackerDao.addNewCourse(course);
        trackerDao.addNewCourse(courseF);
        courses = trackerDao.getAllCourses();
        assertEquals(2, courses.size());
    }

    @Test
    public void getAllAssignments(){
        List<Assignment>assignments;
        Assignment assignment = new Assignment("CST438", "App", "Tuesday", 344.0, 44.0);
        Assignment assignment2 = new Assignment("CST438", "App2", "Wednesday", 4000.0, 99.0);
        trackerDao.addNewAssignment(assignment);
        trackerDao.addNewAssignment(assignment2);
        assignments = trackerDao.getAllAssignments();
        assertEquals(2, assignments.size());
    }

    @Test
    public void deleteUser(){
        List<User>users;
        User user = new User("testUsername","testPassword" );
        trackerDao.addUser(user);
        users = trackerDao.getAllUsers();
        assertEquals("testUsername", users.get(0).getUsername());

        for(User userLoop:users){
            if(userLoop.getUsername().equals("testUsername")){
                trackerDao.deleteUser(userLoop);
            }
        }
        users = trackerDao.getAllUsers();
        assertEquals(0, users.size());
    }

    @Test
    public void deleteCourse(){

        List<Course>courses;
        Course course = new Course("jerry", "dr. c", "cst438", "easy", "tuesday", "wednesday");

        trackerDao.addNewCourse(course);
        courses = trackerDao.getAllCourses();
        assertEquals(1,courses.size());

        for(Course courseLoop:courses){
            if(courseLoop.getTitle().equals("cst438")){
                trackerDao.deleteCourse(courseLoop);
            }
        }
        courses = trackerDao.getAllCourses();
        assertEquals(0, courses.size());

    }

    @Test
    public void deleteAssignment(){

        List<Assignment>assignments;

        Assignment assignment = new Assignment("CST438", "App", "Tuesday", 344.0, 44.0);

        trackerDao.addNewAssignment(assignment);
        assignments = trackerDao.getAllAssignments();

        assertEquals(1,assignments.size());

        for(Assignment assignmentLoop:assignments){
            if(assignmentLoop.getAssignmentName().equals("App")){
                trackerDao.deleteAssignment(assignmentLoop);
            }
        }

        assignments = trackerDao.getAllAssignments();

        assertEquals(0, assignments.size());
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }



}