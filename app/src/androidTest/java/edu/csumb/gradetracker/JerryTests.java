package edu.csumb.gradetracker;

import org.junit.Test;

import java.util.List;

import edu.csumb.gradetracker.model.Course;
import edu.csumb.gradetracker.model.TrackerDao;
import edu.csumb.gradetracker.model.User;

import static org.junit.Assert.assertEquals;

public class JerryTests {

    private static TrackerDao trackerDao;


    @Test
    public void getCoursesForUser() {
        User user = new User("username", "password");
        Course courseE = new Course("Jerry", "Dr. C", "CST438", "easy peasy lemon squeezy", "Tuesday", "Wednesday");
        Course courseD = new Course("Jerry", "Dr. C", "CST438", "easy peasy lemon squeezy", "Tuesday", "Wednesday");

        long uid = user.getId();

        trackerDao.addNewCourse(courseE);
        trackerDao.addNewCourse(courseD);

        List<Course> courses = trackerDao.getCoursesForUser(Integer.toString((int) uid));
        assertEquals(1, courses.size());

        assertEquals(courseE.getTitle(), courses.get(0).getTitle());

    }

    @Test
    public void addUser(){

        User testUser = new User("username", "password");
        trackerDao.addUser(testUser);
        List<User> gettaUser = trackerDao.getAllUsers();

        assertEquals(1, gettaUser.size());

    }

    @Test
    public void addNewCourse(){

        
    }




}