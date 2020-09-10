package edu.csumb.gradetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.csumb.gradetracker.model.Course;
import edu.csumb.gradetracker.model.TrackerDao;
import edu.csumb.gradetracker.model.TrackerRoom;
import edu.csumb.gradetracker.model.User;

public class EditCourseActivity extends AppCompatActivity {

    Course mCourse = ShowCoursesActivity.mCourse;//this is the course selected from listView
    User mUser = MainActivity.mUser;
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
        mCourseTitle.setHint(mCourse.getTitle());
        mCourseDescription.setHint(mCourse.getDescription());
        mStartDate.setHint(mCourse.getStartDate());
        mEndDate.setHint(mCourse.getEndDate());

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitEdit();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCourse();
            }
        });

        returnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditCourseActivity.this,LoggedInActivity.class);
                startActivity(intent);
            }
        });
    }
    boolean submitEdit(){

        String instructorName = mInstructorName.getText().toString();
        String courseTitle = mCourseTitle.getText().toString();
        String courseDescription = mCourseDescription.getText().toString();
        String startDate = mStartDate.getText().toString();
        String endDate = mEndDate.getText().toString();

        //GETS ACCESS TO THE DAO
        TrackerDao dao = TrackerRoom.getTrackerRoom(EditCourseActivity.this).dao();

        //CREATES A COURSE OBJECT OUT OF OUR INPUT AND INSERTS THROUGH DAO METHOD INSERT
        if(!instructorName.isEmpty()){
            mCourse.setInstructor(instructorName);
        }
        if(!courseTitle.isEmpty()){
            mCourse.setTitle(courseTitle);
        }
        if(!courseDescription.isEmpty()){
            mCourse.setDescription(courseDescription);
        }
        if(!startDate.isEmpty()){
            mCourse.setStartDate(startDate);
        }
        if(!endDate.isEmpty()){
            mCourse.setEndDate(endDate);
        }

        dao.updateCourse(mCourse);

        Toast.makeText(this, "Course was updated.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(EditCourseActivity.this,LoggedInActivity.class);
        startActivity(intent);

        return true;
    }

    void deleteCourse(){

        TrackerDao dao = TrackerRoom.getTrackerRoom(EditCourseActivity.this).dao();
        dao.deleteCourse(mCourse);
        Toast.makeText(this, "Course was deleted.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(EditCourseActivity.this,LoggedInActivity.class);
        startActivity(intent);

    }
}