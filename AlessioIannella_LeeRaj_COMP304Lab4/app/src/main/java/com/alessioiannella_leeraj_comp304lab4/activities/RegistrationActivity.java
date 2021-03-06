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

    private TextView textViewErrorRegistrationID;
    private TextView textViewErrorRegistrationFirstName;
    private TextView textViewErrorRegistrationLastName;
    private TextView textViewErrorRegistrationDepartment;
    private TextView textViewErrorRegistrationPassword;
    private TextView textViewErrorRegistrationConfirmPassword;

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

        textViewErrorRegistrationID = (TextView) findViewById(R.id.textViewErrorRegistrationID);
        textViewErrorRegistrationFirstName = (TextView) findViewById(R.id.textViewErrorRegistrationFirstName);
        textViewErrorRegistrationLastName = (TextView) findViewById(R.id.textViewErrorRegistrationLastName);
        textViewErrorRegistrationDepartment = (TextView) findViewById(R.id.textViewErrorRegistrationDepartment);
        textViewErrorRegistrationPassword = (TextView) findViewById(R.id.textViewErrorRegistrationPassword);
        textViewErrorRegistrationConfirmPassword = (TextView) findViewById(R.id.textViewErrorRegistrationConfirmPassword);
        textViewErrorRegistration = (TextView) findViewById(R.id.textViewErrorRegistration);

        hideViews();
    }

    public void handleOnCLikcGoToLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    public void handleOnClickRegister(View view){

        hideViews();

        boolean error = false;

        if (editTextID.getText().toString().isEmpty()){
            textViewErrorRegistrationID.setText("Please enter user ID");
            error = true;
        }
        if (editTextFirstName.getText().toString().isEmpty()){
            textViewErrorRegistrationFirstName.setText("Please enter first name");
            error = true;
        }
        if (editTextLastName.getText().toString().isEmpty()){
            textViewErrorRegistrationLastName.setText("Please enter last name");
            error = true;
        }
        if (editTextDepartment.getText().toString().isEmpty()){
            textViewErrorRegistrationDepartment.setText("Please enter department");
            error = true;
        }
        if (editTextPassword.getText().toString().isEmpty()){
            textViewErrorRegistrationPassword.setText("Please enter password");
            error = true;
        }
        if (editTextConfirmPassword.getText().toString().isEmpty()){
            textViewErrorRegistrationConfirmPassword.setText("Please confirm password");
            error = true;
        }
        if (!editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString())){
            textViewErrorRegistrationConfirmPassword.setText("Passwords do not match");
            error = true;
        }

        if (!error){
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

    public void hideViews(){
        textViewErrorRegistrationID.setText("");
        textViewErrorRegistrationFirstName.setText("");
        textViewErrorRegistrationLastName.setText("");
        textViewErrorRegistrationDepartment.setText("");
        textViewErrorRegistrationPassword.setText("");
        textViewErrorRegistrationConfirmPassword.setText("");

    }
}
