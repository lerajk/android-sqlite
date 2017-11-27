package com.alessioiannella_leeraj_comp304lab4.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alessioiannella_leeraj_comp304lab4.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextID;
    private EditText editTextPassword;
    private TextView textViewLoginError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextID = (EditText) findViewById(R.id.editTextID);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewLoginError = (TextView) findViewById(R.id.textViewLoginError);
    }

    public void handleOnClickLogin(View view){

    }

    public void handleOnClickGoToRegistration(View view){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}
