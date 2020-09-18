package edu.csumb.gradetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.csumb.gradetracker.model.Course;
import edu.csumb.gradetracker.model.TrackerDao;
import edu.csumb.gradetracker.model.TrackerRoom;
import edu.csumb.gradetracker.model.User;

//3.	Edit User
//        a.	Similar to the 'User' Activity but it will allow the user to edit their details

public class EditUserActivity extends AppCompatActivity {

    User mUser = MainActivity.mUser; //getting the user that's logged in's information
    EditText mPassword;
    Button submitButton;
    Button returnMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPassword = findViewById(R.id.new_password);
        submitButton = findViewById(R.id.submit_button);
        returnMainMenu = findViewById(R.id.return_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });

        returnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditUserActivity.this,LoggedInActivity.class);
                startActivity(intent);
            }
        });

    }
    //we get the new password that was typed in the text box, get an instance of the dao, check
    //if the password is empty or not then use a setter on the mUser object and pass it back
    //to the database through the update function
    boolean changePassword(){

        String password = mPassword.getText().toString();

        TrackerDao dao = TrackerRoom.getTrackerRoom(EditUserActivity.this).dao();

        if(!password.isEmpty()&&password!=mUser.getPassword()){

            mUser.setPassword(password);
            dao.updateUser(mUser);
            Toast.makeText(this, "Password change was successful.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(EditUserActivity.this,LoggedInActivity.class);
            startActivity(intent);
            return true;

        }else if(password.isEmpty()){

            Toast.makeText(this, "Password is empty.Try again", Toast.LENGTH_SHORT).show();

        }else if(password.equals(mUser.getPassword())){

            Toast.makeText(this, "This is your current password.Try again", Toast.LENGTH_SHORT).show();

        }

        return false;
    }


}