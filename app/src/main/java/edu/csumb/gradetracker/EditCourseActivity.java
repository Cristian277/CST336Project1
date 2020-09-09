package edu.csumb.gradetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.csumb.gradetracker.model.Course;

public class EditCourseActivity extends AppCompatActivity {

    Course mCourse = ShowCoursesActivity.mCourse;//this is the course selected from listView

    //these are the boxes we type input
    EditText mInstructorName;
    EditText mCourseTitle;
    EditText mCourseDescription;
    EditText mStartDate;
    EditText mEndDate;

    Button submitButton;
    Button returnMainMenu;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mInstructorName = findViewById(R.id.instructor_name);
        mCourseTitle = findViewById(R.id.course_title);
        mCourseDescription = findViewById(R.id.description);
        mStartDate = findViewById(R.id.start_date);
        mEndDate = findViewById(R.id.end_date);
        returnMainMenu = findViewById(R.id.return_button);
        deleteButton = findViewById(R.id.delete_button);
        submitButton = findViewById(R.id.submit_button);

        mInstructorName.setHint(mCourse.getInstructor());
        mCourseTitle.setHint(mCourse.getInstructor());
        mCourseDescription.setHint(mCourse.getInstructor());
        mStartDate.setHint(mCourse.getInstructor());
        mEndDate.setHint(mCourse.getInstructor());




    }
}