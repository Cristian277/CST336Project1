package edu.csumb.gradetracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import edu.csumb.gradetracker.model.TrackerRoom;
import edu.csumb.gradetracker.model.User;

public class MainActivity extends AppCompatActivity {

    List<User> mUserList;
    EditText mUserName;
    EditText mPassword;

    public static final String MAINACTIVITY = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mUserName = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);

        Button sign_in_button = findViewById(R.id.sign_in_button);

        sign_in_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                validateUser();
            }
        });

        Button create_account_button = findViewById(R.id.create_account_button);

        create_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    //CHECK USER
    boolean validateUser(){

        mUserList = TrackerRoom.getTrackerRoom(MainActivity.this).dao().getAllUsers();

        String username = mUserName.getText().toString();
        String password = mPassword.getText().toString();

        for(User existingUser:mUserList){

            if(existingUser.getUsername().equals(username)&&existingUser.getPassword().equals(password)){

                Toast.makeText(this, "User is Valid.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this,LoggedInActivity.class);
                startActivity(intent);

                return true;

            }
        }

        Toast.makeText(this, "Invalid Login info.", Toast.LENGTH_SHORT).show();

        return false;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar row_layout clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
