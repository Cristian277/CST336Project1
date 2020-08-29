package edu.csumb.flightapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import edu.csumb.flightapp.model.Course;
import edu.csumb.flightapp.model.TrackerDao;
import edu.csumb.flightapp.model.TrackerRoom;


public class ShowCoursesActivity extends AppCompatActivity {

    private static final String SHOWCOURSESACTIVITY = "ShowCoursesActivity";

    List<Course> courses;
    Button clear_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_courses);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        clear_button = findViewById(R.id.clear_courses_button);

        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clearCourses();
                Toast.makeText(ShowCoursesActivity.this, "Courses have been cleared.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ShowCoursesActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });

        Button return_main_button = findViewById(R.id.return_button);
        return_main_button.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

        // get all flights from database and assign to flights array
        courses = TrackerRoom.getTrackerRoom(this).dao().getAllCourses();
        Log.d(SHOWCOURSESACTIVITY, "flights count " + courses.size());

        ListView courses_view = findViewById(R.id.course_list);
        //Takes in a course arraylist to display
        courses_view.setAdapter(new CourseListAdapter( this,courses) );
    }

    //DELETES ALL COURSES
    boolean clearCourses(){

        TrackerDao dao = TrackerRoom.getTrackerRoom(ShowCoursesActivity.this).dao();

        for(Course course:courses){

            dao.deleteCourse(course);
        }

        return true;

    }

    public class CourseListAdapter extends ArrayAdapter<Course> {

        public CourseListAdapter(Activity context, List<Course> courses){
            super(context, R.layout.row_layout ,courses);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            LayoutInflater inflater= ShowCoursesActivity.this.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.row_layout, null,true);
            TextView rowField = rowView.findViewById(R.id.row_id);

            //set the value of a row in the ListView to the flight info using toString()
            rowField.setText(courses.get(position).toString());
            return rowView;
        }

    }
}
