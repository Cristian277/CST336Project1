package edu.csumb.gradetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.csumb.gradetracker.model.Assignment;
import edu.csumb.gradetracker.model.Course;
import edu.csumb.gradetracker.model.TrackerDao;
import edu.csumb.gradetracker.model.TrackerRoom;
import edu.csumb.gradetracker.model.User;


public class EditAssignmentActivity extends AppCompatActivity {

    Course mCourse = ShowCoursesActivity.mCourse;//this is the course selected from listView
    User mUser = MainActivity.mUser;
    Assignment mAssignment = ShowAssignmentsActivity.mAssignment;

    //these are the boxes we type input
    EditText mAssignmentName;
    EditText mDueDate;

    Button submitButton;
    Button returnToCourses;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assignment);

        mAssignmentName = findViewById(R.id.assignment_name);
        mDueDate = findViewById(R.id.due_date);

        returnToCourses = findViewById(R.id.return_button);
        deleteButton = findViewById(R.id.delete_button);
        submitButton = findViewById(R.id.submit_button);

        String stringEarnedScore = Double.toString(mAssignment.getEarnedScore());
        String stringMaxScore = Double.toString(mAssignment.getMaxScore());

        mAssignmentName.setHint(mAssignment.getAssignmentName());
        mDueDate.setHint(mAssignment.getDueDate());

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitEdit();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAssignment();
            }
        });

        returnToCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditAssignmentActivity.this,ShowCoursesActivity.class);
                startActivity(intent);
            }
        });

    }

    boolean submitEdit(){

        String assignmentName = mAssignmentName.getText().toString();
        String assignmentDueDate = mDueDate.getText().toString();

        //Double earnedScore = Double.parseDouble(mEarnedScore.getText().toString());
        //Double maxScore = Double.parseDouble(mMaxScore.getText().toString());

        //GETS ACCESS TO THE DAO
        TrackerDao dao = TrackerRoom.getTrackerRoom(EditAssignmentActivity.this).dao();

        //CREATES A COURSE OBJECT OUT OF OUR INPUT AND INSERTS THROUGH DAO METHOD INSERT
        if(!assignmentName.isEmpty()){
            mAssignment.setAssignmentName(assignmentName);
        }
        if(!assignmentDueDate.isEmpty()){
            mAssignment.setDueDate(assignmentDueDate);
        }

        dao.updateAssignment(mAssignment);

        Toast.makeText(this, "Assignment was updated.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(EditAssignmentActivity.this,LoggedInActivity.class);
        startActivity(intent);

        return true;
    }

    void deleteAssignment(){

        TrackerDao dao = TrackerRoom.getTrackerRoom(EditAssignmentActivity.this).dao();
        dao.deleteAssignment(mAssignment);
        Toast.makeText(this, "Assignment was deleted.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(EditAssignmentActivity.this,LoggedInActivity.class);
        startActivity(intent);

    }

}