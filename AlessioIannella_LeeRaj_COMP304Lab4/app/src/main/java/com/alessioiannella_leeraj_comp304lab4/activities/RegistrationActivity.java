package com.alessioiannella_leeraj_comp304lab4.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alessioiannella_leeraj_comp304lab4.R;
import com.alessioiannella_leeraj_comp304lab4.exceptions.DuplicateIDException;
import com.alessioiannella_leeraj_comp304lab4.exceptions.NurseNotFoundException;
import com.alessioiannella_leeraj_comp304lab4.helpers.DBHelper;
import com.alessioiannella_leeraj_comp304lab4.models.Doctor;
import com.alessioiannella_leeraj_comp304lab4.models.Nurse;

import org.w3c.dom.Text;

public class RegistrationActivity extends AppCompatActivity {

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

    public void handleOnCLikcGoToLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

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

        if (radioButtonNurse.isChecked()){

            try{
                DBHelper dbHelper = new DBHelper(this);

                Nurse nurse = new Nurse();
                nurse.setNurseID(editTextID.getText().toString());
                nurse.setFirstName(editTextFirstName.getText().toString());
                nurse.setLastName(editTextLastName.getText().toString());
                nurse.setDepartment(editTextDepartment.getText().toString());
                nurse.setPassword(editTextPassword.getText().toString());

                dbHelper.addNurse(this, nurse);

                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
            catch (DuplicateIDException e) {
                textViewErrorRegistration.setText(e.getLocalizedMessage());
            }

        }
        else if (radioButtonDoctor.isChecked()){
            try{
                DBHelper dbHelper = new DBHelper(this);

                Doctor doctor = new Doctor();
                doctor.setDoctorID(editTextID.getText().toString());
                doctor.setFirstName(editTextFirstName.getText().toString());
                doctor.setLastName(editTextLastName.getText().toString());
                doctor.setDepartment(editTextDepartment.getText().toString());
                doctor.setPassword(editTextPassword.getText().toString());

                dbHelper.addDoctor(this, doctor);

                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
            catch (DuplicateIDException e) {
                textViewErrorRegistration.setText(e.getLocalizedMessage());
            }
        }

    }
}
