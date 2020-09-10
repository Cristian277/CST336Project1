package edu.csumb.gradetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.csumb.gradetracker.model.Assignment;
import edu.csumb.gradetracker.model.TrackerDao;
import edu.csumb.gradetracker.model.TrackerRoom;


public class AddAssignmentActivity extends AppCompatActivity {

    EditText mCourseTitle;
    EditText mAssignmentName;
    EditText mDueDate;
    EditText mEarnedScore;
    EditText mMaxScore;

    Button mSubmitButton;
    Button mReturnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        mAssignmentName = findViewById(R.id.assignmentName);
        mDueDate = findViewById(R.id.due_date);
        mEarnedScore = findViewById(R.id.earned_score);
        mMaxScore = findViewById(R.id.max_score);
        mCourseTitle = findViewById(R.id.course_title);

        mReturnButton = findViewById(R.id.return_button);
        mSubmitButton = findViewById(R.id.submit_button);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewAssignment();
            }
        });

        mReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAssignmentActivity.this, LoggedInActivity.class);
                startActivity(intent);
            }
        });
    }

    boolean addNewAssignment(){
        String assignmentName = mAssignmentName.getText().toString();
        String dueDate = mDueDate.getText().toString();
        String courseTitle = mCourseTitle.getText().toString();

        long earnedScore = Long.parseLong(mEarnedScore.getText().toString());
        long maxScore = Long.parseLong(mMaxScore.getText().toString());

        //GET ACCESS TO THE DAO
        TrackerDao dao = TrackerRoom.getTrackerRoom(AddAssignmentActivity.this).dao();

        //CREATES AN ASSIGNMENT OBJECT OUT OF OUR INPUT AND INSERTS THROUGH THE DAO METHOD INSERT
        Assignment assignment = new Assignment(courseTitle,assignmentName,dueDate,earnedScore,maxScore);
        dao.addNewAssignment(assignment);

        Toast.makeText(this, "Assignment was added.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AddAssignmentActivity.this, LoggedInActivity.class);
        startActivity(intent);

        return true;
    }

}