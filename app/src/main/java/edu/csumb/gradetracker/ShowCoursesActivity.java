package edu.csumb.gradetracker;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import edu.csumb.gradetracker.model.Course;
import edu.csumb.gradetracker.model.TrackerDao;
import edu.csumb.gradetracker.model.TrackerRoom;
import edu.csumb.gradetracker.model.User;


public class ShowCoursesActivity extends AppCompatActivity {

    List<Course> courses;
    Button clear_button;
    User mUser = MainActivity.mUser;
    static Course mCourse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_courses);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        courses = TrackerRoom.getTrackerRoom(this).dao().getCoursesForUser(mUser.getUsername());

        ListView courses_view = findViewById(R.id.course_list);
        courses_view.setAdapter( new CourseListAdapter(this,courses));

        if(courses.isEmpty()){

            AlertDialog.Builder builder = new AlertDialog.Builder(ShowCoursesActivity.this);

            String msg = "There are currently no courses to display.";
            builder.setTitle(msg);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            builder.show();

        }

        clear_button = findViewById(R.id.clear_courses_button);

        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clearCourses();
                Toast.makeText(ShowCoursesActivity.this, "Courses have been cleared.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ShowCoursesActivity.this,LoggedInActivity.class);
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

        //ListView courses_view = findViewById(R.id.course_list);
        //Takes in a course arraylist to display
        //courses_view.setAdapter(new CourseListAdapter( this,courses) );

        courses_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //make course object = the course at i which is the box clicked
                mCourse = courses.get(position);
                Intent intent = new Intent(getApplicationContext(),EditCourseActivity.class);
                startActivity(intent);
            }
        });
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
