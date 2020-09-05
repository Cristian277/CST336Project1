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

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validateUser()){
                    count++;
                }
                if(count==2){

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

    boolean validateUser() {

        String userName = mUserName.getText().toString();
        String password = mPassword.getText().toString();

        int alphabetCountUserName = 0;
        int digitCountUserName = 0;

        int alphabetCountPassword = 0;
        int digitCountPassword = 0;

        int specialCharacterUser = 0;
        int specialCharacterPassword = 0;

        boolean userNameCorrect = false;
        boolean passwordCorrect = false;

        boolean hasUppercasePass = !password.equals(password.toLowerCase());
        boolean hasLowercasePass = !password.equals(password.toUpperCase());

        boolean hasUppercaseUser = !userName.equals(userName.toLowerCase());
        boolean hasLowercaseUser = !userName.equals(userName.toUpperCase());

        if (userName.isEmpty() || password.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Empty Entry.", Toast.LENGTH_SHORT).show();

        } else {

            for (int x = 0; x < userName.length(); x++) {

                if (userName.charAt(x) >= 65 && userName.charAt(x) <= 90 || (userName.charAt(x) >= 97 && userName.charAt(x) <= 122)) {
                    alphabetCountUserName++;
                } else if (userName.charAt(x) >= 48 && userName.charAt(x) <= 57) {
                    digitCountUserName++;
                }
            }

            for (int x = 0; x < userName.length(); x++) {

                if (userName.charAt(x) == '!' || userName.charAt(x) == '@' || (userName.charAt(x) == '#' || userName.charAt(x) == '$')) {
                    specialCharacterUser++;
                }
            }

            for (int x = 0; x < password.length(); x++) {

                if (password.charAt(x) >= 65 && password.charAt(x) <= 90 || (password.charAt(x) >= 97 && password.charAt(x) <= 122)) {
                    alphabetCountPassword++;
                } else if (password.charAt(x) >= 48 && password.charAt(x) <= 57) {
                    digitCountPassword++;
                }
            }

            for (int x = 0; x < password.length(); x++) {

                if (password.charAt(x) == '!' || password.charAt(x) == '@' || (password.charAt(x) == '#' || password.charAt(x) == '$')) {
                    specialCharacterPassword++;
                }
            }

            if (alphabetCountUserName > 0 && digitCountUserName > 0 && specialCharacterUser > 0 && hasUppercaseUser && hasLowercaseUser) {
                userNameCorrect = true;
            } else {
                Toast.makeText(getApplicationContext(), "Invalid Username.", Toast.LENGTH_SHORT).show();
            }

            if (alphabetCountPassword > 0 && digitCountPassword >0 && specialCharacterPassword > 0 && hasUppercasePass && hasLowercasePass) {
                passwordCorrect = true;
            } else {
                Toast.makeText(getApplicationContext(), "Invalid Password.", Toast.LENGTH_SHORT).show();
            }

        }

        boolean inList = false;

        mUsers = TrackerRoom.getTrackerRoom(CreateAccountActivity.this).dao().getAllUsers(); //get all logs

        for (User existingUser : mUsers) { //check records for duplicates
            if (existingUser.getUsername().equals(userName)) {
                Toast.makeText(getApplicationContext(), "Username already exists, please try again.", Toast.LENGTH_SHORT).show();
                inList = true;
                break;
            }
        }

        if (!inList && userNameCorrect && passwordCorrect) { //if the user is not in the list then a new user is created with the username and password

            User newUser = new User(userName, password);

            TrackerDao dao = TrackerRoom.getTrackerRoom(CreateAccountActivity.this).dao();
            dao.addUser(newUser);

            Toast.makeText(getApplicationContext(), "Account Created Succesfully.", Toast.LENGTH_SHORT).show(); //toast showing that the account creation was successful

            Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);

            startActivity(intent); //returns to main menu
            return true;
        }

        return false;
    }

}
