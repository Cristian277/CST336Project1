package edu.csumb.gradetracker.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TrackerDao {

    //these are all the functions for the USER java class
    @Query("select * from User")
    List<User> getAllUsers();

    @Insert
    long addUser(User user);

    @Update
    int updateUser(User user);

    @Delete
    void deleteUser(User user);

    //these are all the functions for the COURSE JAVA CLASS

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

    //these are all the functions for the ASSIGNMENT CLASS

    @Query("select * from Assignment")
    List<Assignment> getAllAssignments();

    @Query("select * from Assignment where courseTitle = :title")
    List<Assignment> getAssignmentsForCourse(String title);

    @Insert
    long addNewAssignment(Assignment assignment);

    @Update
    int updateAssignment(Assignment assignment);

    @Delete
    void deleteAssignment(Assignment assignment);

}
