package edu.csumb.gradetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import edu.csumb.gradetracker.model.Course;
import edu.csumb.gradetracker.model.TrackerDao;
import edu.csumb.gradetracker.model.TrackerRoom;


public class AddCourseActivity extends AppCompatActivity {

    private static final String ADD_COURSE_ACTIVITY = "AddCourseActivity";


    EditText mInstructorName;
    EditText mCourseTitle;
    EditText mCourseDescription;
    EditText mStartDate;
    EditText mEndDate;

    Button addCourseButton;
    Button returnMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addCourseButton = findViewById(R.id.add_button);
        returnMainMenu = findViewById(R.id.return_button);

        mInstructorName = findViewById(R.id.instructor_name);
        mCourseTitle = findViewById(R.id.course_title);
        mCourseDescription = findViewById(R.id.description);
        mStartDate = findViewById(R.id.start_date);
        mEndDate = findViewById(R.id.end_date);

        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addCourse();

            }
        });

        returnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddCourseActivity.this,LoggedInActivity.class);
                startActivity(intent);
            }
        });
    }

    boolean addCourse(){

        String instructorName = mInstructorName.getText().toString();
        String courseTitle = mCourseTitle.getText().toString();
        String courseDescription = mCourseDescription.getText().toString();
        String startDate = mStartDate.getText().toString();
        String endDate = mEndDate.getText().toString();

        //GETS ACCESS TO THE DAO
        TrackerDao dao = TrackerRoom.getTrackerRoom(AddCourseActivity.this).dao();

        //CREATES A COURSE OBJECT OUT OF OUR INPUT AND INSERTS THROUGH DAO METHOD INSERT
        Course newCourse = new Course(instructorName,courseTitle,courseDescription,startDate,endDate);
        dao.addNewCourse(newCourse);

        Toast.makeText(this, "Course was added.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(AddCourseActivity.this,LoggedInActivity.class);
        startActivity(intent);

        return true;
    }


}