package com.example.agrofarmers;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final AutoCompleteTextView emailTextView = (AutoCompleteTextView)findViewById(R.id.email);
        final EditText passwordEditText = (EditText) findViewById(R.id.password);

        Firebase.setAndroidContext(this);

        final Firebase rootRef = new Firebase("https://farmers-518e5.firebaseio.com/");

        TextView sign_up = (TextView)findViewById(R.id.signup_prompt);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button login = (Button)findViewById(R.id.email_sign_in_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage(emailTextView.getText().toString() + " -> " + passwordEditText.getText().toString())
                        .setTitle("Congratulation!!!")
                        .setPositiveButton(android.R.string.ok, null);
                AlertDialog dialog = builder.create();
                dialog.show();

                Intent intent = new Intent(getApplicationContext(), RequestActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
