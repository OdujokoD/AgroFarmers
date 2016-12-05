package com.example.agrofarmers;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class SignupActivity extends AppCompatActivity {

    public static final String FIREBASE_URL = "https://agro-transport.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final AutoCompleteTextView firstNameObj = (AutoCompleteTextView)findViewById(R.id.signup_firstname);
        final AutoCompleteTextView lastNameObj = (AutoCompleteTextView)findViewById(R.id.signup_lastname);
        final AutoCompleteTextView emailObj = (AutoCompleteTextView)findViewById(R.id.signup_email);
        final EditText passwordObj = (EditText)findViewById(R.id.signup_password);

        Firebase.setAndroidContext(this);

        final Firebase ref = new Firebase(FIREBASE_URL);

        TextView sign_up = (TextView)findViewById(R.id.signin_prompt);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button signup = (Button)findViewById(R.id.email_sign_up_button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameObj.getText().toString();
                String lastName = lastNameObj.getText().toString();
                String password = passwordObj.getText().toString();
                String email = emailObj.getText().toString();

                password = password.trim();
                email = email.trim();


                if (firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    builder.setMessage(R.string.signup_error_message)
                            .setTitle(R.string.signup_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {

                    // signup
                    ref.createUser(email, password, new Firebase.ResultHandler() {
                        @Override
                        public void onSuccess() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                            builder.setMessage(R.string.signup_success)
                                    .setPositiveButton(R.string.login_button_label, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }
                                    });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                            builder.setMessage(firebaseError.getMessage())
                                    .setTitle(R.string.signup_error_title)
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });
                }
            }
        });

    }
}
