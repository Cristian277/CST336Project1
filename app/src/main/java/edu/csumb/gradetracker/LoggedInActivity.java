package edu.csumb.gradetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.csumb.gradetracker.model.User;

public class LoggedInActivity extends AppCompatActivity {

    TextView mDisplayName;
    User mUser = MainActivity.mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        mDisplayName = findViewById(R.id.welcomeMessage);

        mDisplayName.setText("Hello " + mUser.getUsername() + "!");

        //TAKES USER TO ADD COURSES ACTIVITY
        Button add_courses_button = findViewById(R.id.add_courses_button);

        add_courses_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoggedInActivity.this,AddCourseActivity.class);
                startActivity(intent);
            }
        });

        //TAKE THE USER TO COURSES ADDED
        Button show_courses_button = findViewById(R.id.show_courses_button);

        show_courses_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedInActivity.this, ShowCoursesActivity.class);
                startActivity(intent);
            }
        });

        //LOG THE USER OUT
        Button log_out_button = findViewById(R.id.log_out_button);

        log_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoggedInActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }
}
