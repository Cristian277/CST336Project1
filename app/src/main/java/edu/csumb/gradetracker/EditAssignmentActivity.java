package edu.csumb.gradetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


//7.	Add/Edit/Delete Assignment
//        a.	Users will be able to add assignments to a course
//        b.	User will be able to change the values of existing courses


public class EditAssignmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assignment);
    }
}