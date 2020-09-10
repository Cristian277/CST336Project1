package edu.csumb.gradetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        //TAKE USER TO SHOW ASSIGNMENTS ACTIVITY
        Button show_assignment_btn = findViewById(R.id.showAsmtBtn);
        show_assignment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedInActivity.this, ShowAssignmentsActivity.class);
                startActivity(intent);
            }
        });

        //TAKE THE USER TO ADD ASSIGNMENTS ACTIVITY
        Button add_assignment_btn = findViewById(R.id.addAssignmentBtn);
        add_assignment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedInActivity.this, AddAssignmentActivity.class);
                startActivity(intent);
            }
        });

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

        Button change_password_button = findViewById(R.id.change_password_button);

        change_password_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoggedInActivity.this,EditUserActivity.class);
                startActivity(intent);
            }
        });

        //TODO: Implement another class called
        // 3.	Edit User: Similar to the 'User' Activity but it will allow the user to edit their details
        // - Needs another button added to this activity to launch EditUserActivity.class

        //TODO: Implement ShowAssignmentsActivity.class
        // - Need to add a button to launch intent

        //TODO: Implement EditAssignmentActivity.class
        // - Need to add a button to launch intent



    }
}
