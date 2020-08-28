package edu.csumb.flightapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoggedInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        Button add_courses_button = findViewById(R.id.add_courses_button);

        add_courses_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoggedInActivity.this,AddCourseActivity.class);
                startActivity(intent);
            }
        });

        Button show_courses_button = findViewById(R.id.show_courses_button);

        show_courses_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedInActivity.this, ShowCoursesActivity.class);
                startActivity(intent);
            }
        });


    }
}
