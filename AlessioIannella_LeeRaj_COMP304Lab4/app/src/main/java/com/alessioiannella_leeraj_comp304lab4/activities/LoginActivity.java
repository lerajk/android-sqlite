package com.alessioiannella_leeraj_comp304lab4.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alessioiannella_leeraj_comp304lab4.R;
import com.alessioiannella_leeraj_comp304lab4.exceptions.LoginFailedException;
import com.alessioiannella_leeraj_comp304lab4.helpers.DBHelper;

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

        if (editTextID.getText().toString().isEmpty()){
            textViewLoginError.setText("Insert ID");
            return;
        }
        if (editTextPassword.getText().toString().isEmpty()){
            textViewLoginError.setText("Insert password");
            return;
        }

        DBHelper dbHelper = new DBHelper(this);

        try{
            dbHelper.login(this, editTextID.getText().toString(), editTextPassword.getText().toString());

            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        catch (LoginFailedException e) {
            textViewLoginError.setText(e.getLocalizedMessage());
        }

    }

    public void handleOnClickGoToRegistration(View view){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}
