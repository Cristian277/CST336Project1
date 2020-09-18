package edu.csumb.gradetracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

import edu.csumb.gradetracker.model.TrackerDao;
import edu.csumb.gradetracker.model.TrackerRoom;
import edu.csumb.gradetracker.model.User;

public class CreateAccountActivity  extends AppCompatActivity {

    //Setting up the edit text and list to hold users
    EditText mUserName;
    EditText mPassword;
    List<User> mUsers;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button create_button = findViewById(R.id.create_account_button);
        mUserName = findViewById(R.id.username); //set to the text typed in
        mPassword = findViewById(R.id.password);

        //when the create_button is called validateUser() is called inside of it
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if validate user returns false the counter goes up and too many wrong attempts kick you out
                if(!validateUser()) count++;
                if(count==4){

                    count=0;

                    AlertDialog.Builder builder = new AlertDialog.Builder(CreateAccountActivity.this);

                    String msg = "Too many wrong attempts. Returning to Main Screen.";
                    builder.setTitle(msg);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);

                            startActivity(intent); //returns to main menu
                        }
                    });

                    builder.show();
                }

            }
        });
    }

    //this is a function to make sure the username and password meet the right requirements
    boolean validateUser() {

        String userName = mUserName.getText().toString();
        String password = mPassword.getText().toString();

        if (userName.isEmpty() || password.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Empty Entry.", Toast.LENGTH_SHORT).show();
        }

        //if the username and password are correct and the user is not in the database then add them
        if(checkCharacters(userName)&&checkCharacters(password)&&!userInDatabase(userName)){

            User newUser = new User(userName, password);

            //getting dao information to add user
            TrackerDao dao = TrackerRoom.getTrackerRoom(CreateAccountActivity.this).dao();
            dao.addUser(newUser);

            Toast.makeText(getApplicationContext(), "Account Created Successfully.", Toast.LENGTH_SHORT).show(); //toast showing that the account creation was successful

            Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);

            startActivity(intent); //returns to main menu

            return true;

        }
        return false;
    }
    //function to check the username and password for the right characters
    boolean checkCharacters(String userNameOrPassword){

        //set up counters for each requirement
        int alphabetCount = 0;
        int digitCount = 0;

        int specialCharacter = 0;

        boolean hasUppercase = !userNameOrPassword.equals(userNameOrPassword.toLowerCase());
        boolean hasLowercase = !userNameOrPassword.equals(userNameOrPassword.toUpperCase());

        for (int x = 0; x < userNameOrPassword.length(); x++) {

            if (userNameOrPassword.charAt(x) >= 65 && userNameOrPassword.charAt(x) <= 90 || (userNameOrPassword.charAt(x) >= 97 && userNameOrPassword.charAt(x) <= 122)) {
                alphabetCount++;
            } else if (userNameOrPassword.charAt(x) >= 48 && userNameOrPassword.charAt(x) <= 57) {
                digitCount++;
            }
        }

        for (int x = 0; x <userNameOrPassword.length(); x++) {

            if (userNameOrPassword.charAt(x) == '!' || userNameOrPassword.charAt(x) == '@' || (userNameOrPassword.charAt(x) == '#' || userNameOrPassword.charAt(x) == '$')) {
                specialCharacter++;
            }
        }

        if (alphabetCount > 0 && digitCount > 0 && specialCharacter > 0 && hasUppercase && hasLowercase) {
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Invalid Username.", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    //function that gets list of users and checks if the username matches up with any of them
    //before actually creating the user and adding it to the database
    boolean userInDatabase(String userName){

        mUsers = TrackerRoom.getTrackerRoom(CreateAccountActivity.this).dao().getAllUsers(); //get all logs

        for (User existingUser : mUsers) { //check records for duplicates
            if (existingUser.getUsername().equals(userName)) {
                Toast.makeText(getApplicationContext(), "Username already exists, please try again.", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }

}
