package edu.csumb.gradetracker.model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TrackerDao {

    //THESE ARE ALL OUR FUNCTIONS FOR THE USER JAVA CLASS
    @Query("select * from User")
    List<User> getAllUsers();

    // the generated id value is returned
    @Insert
    long addUser(User user);

    // return the number of rows actually updated.  Should be 1
    @Update
    int updateUser(User user);

    @Delete
    void deleteUser(User user);

    //THESE ARE ALL THE FUNCTIONS FOR THE COURSE JAVA CLASS

    @Query("select * from Course")
    List<Course> getAllCourses();

    @Query("select * from Course where username = :name")
    List<Course> getCoursesForUser(String name);

    @Insert
    long addNewCourse(Course course);

    @Update
    int updateCourse(Course course);

    @Delete
    void deleteCourse(Course course);

}
