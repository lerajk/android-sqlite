package com.alessioiannella_leeraj_comp304lab4.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alessioiannella_leeraj_comp304lab4.R;

import org.w3c.dom.Text;

public class RegistrationActivity extends BaseActivity {

    private RadioButton radioButtonNurse;
    private RadioButton radioButtonDoctor;
    private EditText editTextID;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextDepartment;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private TextView textViewErrorRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void handleOnCLikcGoToLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        radioButtonNurse = (RadioButton) findViewById(R.id.radioButtonNurse);
        radioButtonDoctor = (RadioButton) findViewById(R.id.radioButtonDoctor);

        editTextID = (EditText) findViewById(R.id.editTextID);
        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        editTextDepartment = (EditText) findViewById(R.id.editTextDepartment);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);

        textViewErrorRegistration = (TextView) findViewById(R.id.textViewErrorRegistration);

    }

    public void handleOnClickRegister(View view){

        if (editTextID.getText().toString().isEmpty()){
            textViewErrorRegistration.setText("Please enter ID");
            return;
        }
        if (editTextFirstName.getText().toString().isEmpty()){
            textViewErrorRegistration.setText("Please enter First Name");
            return;
        }
        if (editTextLastName.getText().toString().isEmpty()){
            textViewErrorRegistration.setText("Please enter Last Name");
            return;
        }
        if (editTextDepartment.getText().toString().isEmpty()){
            textViewErrorRegistration.setText("Please enter Department");
            return;
        }
        if (editTextPassword.getText().toString().isEmpty()){
            textViewErrorRegistration.setText("Please enter Password");
            return;
        }
        if (editTextConfirmPassword.getText().toString().isEmpty()){
            textViewErrorRegistration.setText("Please confirm password");
            return;
        }
        if (!editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString())){
            textViewErrorRegistration.setText("Passwords do not match");
            return;
        }

    }
}
