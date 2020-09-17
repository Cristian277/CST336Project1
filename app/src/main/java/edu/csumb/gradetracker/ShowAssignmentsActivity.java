package edu.csumb.gradetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.csumb.gradetracker.model.Assignment;
import edu.csumb.gradetracker.model.Course;
import edu.csumb.gradetracker.model.TrackerDao;
import edu.csumb.gradetracker.model.TrackerRoom;
import edu.csumb.gradetracker.model.User;

//6.	Assignment
//        a.	Shows the assignment detail, score, etc.

public class ShowAssignmentsActivity extends AppCompatActivity {

    List<Assignment> assignments;

    Button clear_button;
    Button returnToMainMenuButton;
    Button addAssignmentButton;

    User mUser = MainActivity.mUser;
    static Course mCourse = ShowCoursesActivity.mCourse;
    static Assignment mAssignment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_assignments);

        returnToMainMenuButton = findViewById(R.id.return_button);
        clear_button = findViewById(R.id.clear_assignments_button);
        addAssignmentButton = findViewById(R.id.add_assignment_button);

        assignments = TrackerRoom.getTrackerRoom(ShowAssignmentsActivity.this).dao().getAssignmentsForCourse(mCourse.getTitle());

        ListView assignmentsView = findViewById(R.id.assignment_list);
        //Takes in a course arraylist to display
        assignmentsView.setAdapter(new ShowAssignmentsActivity.AssignmentListAdapter( this,assignments) );

        assignmentsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //make course object = the course at i which is the box clicked
                mAssignment = assignments.get(position);
                Intent intent = new Intent(getApplicationContext(),EditAssignmentActivity.class);
                startActivity(intent);
            }
        });

        addAssignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowAssignmentsActivity.this,AddAssignmentActivity.class);
                startActivity(intent);
            }
        });

        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAssignments();
                Toast.makeText(ShowAssignmentsActivity.this, "Assignments have been cleared.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ShowAssignmentsActivity.this,LoggedInActivity.class);
                startActivity(intent);
            }
        });

        returnToMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowAssignmentsActivity.this,LoggedInActivity.class);
                startActivity(intent);
            }
        });
    }

    boolean clearAssignments(){

        TrackerDao dao = TrackerRoom.getTrackerRoom(ShowAssignmentsActivity.this).dao();

        for(Assignment assignment:assignments){

            dao.deleteAssignment(assignment);
        }

        return true;

    }

    public class AssignmentListAdapter extends ArrayAdapter<Assignment> {

        public AssignmentListAdapter(Activity context, List<Assignment> assignments){
            super(context, R.layout.row_layout ,assignments);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            LayoutInflater inflater= ShowAssignmentsActivity.this.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.row_layout, null,true);
            TextView rowField = rowView.findViewById(R.id.row_id);

            //set the value of a row in the ListView to the flight info using toString()
            rowField.setText(assignments.get(position).toString());
            return rowView;
        }
    }
}