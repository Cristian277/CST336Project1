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
import java.util.List;

import edu.csumb.gradetracker.model.TrackerDao;
import edu.csumb.gradetracker.model.TrackerRoom;
import edu.csumb.gradetracker.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

/*
    1. Insert and Test a User in the Database
        a. Test to see if user was entered (assertEquals)
    2. Delete the user from the database
        a. Delete and test if the user was deleted(Cycle DB and assertNull)
    3.
 */
@RunWith(AndroidJUnit4.class)
public class UserTest {

    private static TrackerDao trackerDao;
    private static TrackerRoom db;
    private static User user;

    //Creates a database in memory with the context of the database on file
    @Before
    public void createDb(){
            Context context = ApplicationProvider.getApplicationContext();
            db = Room.inMemoryDatabaseBuilder(context, TrackerRoom.class).build();
            trackerDao = db.getTrackerRoom(context).dao();
    }

    @Test
    public void useAppContext() { //irrelevent
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("edu.csumb.gradetracker", appContext.getPackageName());
    }

    //Checks if we can add users to the database
    @Test
    public void insertUserTest(){
        user = new User("testUsername","testPassword" );
        trackerDao.addUser(user);
        assertEquals("testUsername", user.getUsername());
        assertEquals("testPassword", user.getPassword());
    }

    @Test
    public void getUsernameTest(){
        user = new User("testUsername","testPassword" );
        assertEquals("testUsername", user.getUsername());
    }

    @Test
    public void getPasswordTest(){
        user = new User("testUsername","testPassword" );
        assertEquals("testPassword", user.getPassword());
        user.setPassword("passwordChange");
        assertNotEquals("testUsername", user.getPassword());
        assertNotEquals("passwordChange", user.getPassword());
    }

    //Tests getId & setId.
    @Test
    public void getIdTest(){
        User user = new User();
        user.setId(33);
        assertEquals(33, user.getId());
    }

    @Test
    public void setPasswordTest(){
        User user = new User();
        user.setPassword("s");
        assertEquals("s", user.getPassword());
        user = new User("testUsername", "testPassword");
        assertEquals(0, user.getId());

    }

    //Checks if we can delete users from the database
    @Test
    public void deleteUser(){
        User user = new User("testUsername","testPassword" );
        trackerDao.addUser(user);
        assertEquals("testUsername", user.getUsername());
        List<User> users = trackerDao.getAllUsers();
        trackerDao.deleteUser(user);
        assertNotEquals("0", users.size());
    }

    @After
    public void closeDb() throws IOException{
        db.close();
    }

}
