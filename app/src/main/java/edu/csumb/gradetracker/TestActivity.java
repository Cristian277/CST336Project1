package edu.csumb.gradetracker;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import edu.csumb.gradetracker.model.TrackerDao;
import edu.csumb.gradetracker.model.TrackerRoom;
import edu.csumb.gradetracker.model.User;

public class TestActivity extends AppCompatActivity {

    private static final String TEST_ACTIVITY = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testdb);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button return_main_button = findViewById(R.id.return_button);
        return_main_button.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TEST_ACTIVITY, "onClick return called");
                finish();
            }
        });

        TrackerDao dao = TrackerRoom.getTrackerRoom(this).dao();
        List<User> users = dao.getAllUsers();
        TextView users_test = findViewById(R.id.users_test);
        //IF THERE ARE ANY USERS IN THE DATABASE THEN THIS TEST WILL PASS AND TURN GREEN
        //IF THERE ARE NO USERS THE TEST IS RED SINCE IT'S LESS THAN ONE
        if (users.size()>= 1) users_test.setTextColor(Color.GREEN);
    }

}