package edu.csumb.gradetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


//6.	Assignment
//        a.	Shows the assignment detail, score, etc.
//        b.	Should be able to edit the assignment values

public class EditAssignmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assignment);
    }
}