package com.alessioiannella_leeraj_comp304lab4.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alessioiannella_leeraj_comp304lab4.R;
import com.alessioiannella_leeraj_comp304lab4.exceptions.DuplicateIDException;
import com.alessioiannella_leeraj_comp304lab4.helpers.DBHelper;
import com.alessioiannella_leeraj_comp304lab4.models.Patient;

public class PatientActivity extends AppCompatActivity {

    private EditText editTextAddPatientID;
    private EditText editTextAddPatientFirstName;
    private EditText editTextAddPatientLastName;
    private EditText editTextAddPatientDepartment;
    private EditText editTextAddPatientRoom;

    private TextView textViewErrorAddPatientID;
    private TextView textViewErrorAddPatientFirstName;
    private TextView textViewErrorAddPatientLastName;
    private TextView textViewErrorAddPatientDepartment;
    private TextView textViewErrorAddPatientRoom;
    private TextView textViewErrorSubmitPatient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        editTextAddPatientID = (EditText) findViewById(R.id.editTextAddPatientID);
        editTextAddPatientFirstName = (EditText) findViewById(R.id.editTextAddPatientFirstName);
        editTextAddPatientLastName = (EditText) findViewById(R.id.editTextAddPatientLastName);
        editTextAddPatientDepartment = (EditText) findViewById(R.id.editTextAddPatientDepartment);
        editTextAddPatientRoom = (EditText) findViewById(R.id.editTextAddPatientRoom);

        textViewErrorAddPatientID = (TextView) findViewById(R.id.textViewErrorAddPatientID);
        textViewErrorAddPatientFirstName = (TextView) findViewById(R.id.textViewErrorAddPatientFirstName);
        textViewErrorAddPatientLastName = (TextView) findViewById(R.id.textViewErrorAddPatientLastName);
        textViewErrorAddPatientDepartment = (TextView) findViewById(R.id.textViewErrorAddPatientDepartment);
        textViewErrorAddPatientRoom = (TextView) findViewById(R.id.textViewErrorAddPatientRoom);
        textViewErrorSubmitPatient = (TextView) findViewById(R.id.textViewErrorSubmitPatient);


    }

    public void handleOnClickSubmitPatient(View view) {
        if (editTextAddPatientID.getText().toString().isEmpty()){
            textViewErrorAddPatientID.setText("Please enter patient ID");
            return;
        }
        if (editTextAddPatientFirstName.getText().toString().isEmpty()){
            textViewErrorAddPatientFirstName.setText("Please enter patient first name");
            return;
        }
        if (editTextAddPatientLastName.getText().toString().isEmpty()){
            textViewErrorAddPatientLastName.setText("Please enter patient last name");
            return;
        }
        if (editTextAddPatientDepartment.getText().toString().isEmpty()){
            textViewErrorAddPatientDepartment.setText("Please enter patient department");
            return;
        }
        if (editTextAddPatientRoom.getText().toString().isEmpty()){
            textViewErrorAddPatientRoom.setText("Please enter patient room");
            return;
        }

        SharedPreferences sharedPref = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String doctorID = sharedPref.getString("id", "N/A");

        try{
            Patient patient = new Patient();
            patient.setPatientID(editTextAddPatientID.getText().toString());
            patient.setFirstName(editTextAddPatientFirstName.getText().toString());
            patient.setLastName(editTextAddPatientLastName.getText().toString());
            patient.setDepartment(editTextAddPatientDepartment.getText().toString());
            patient.setDoctorID(doctorID);
            patient.setRoom(editTextAddPatientRoom.getText().toString());

            DBHelper dbHelper = new DBHelper(this);

            dbHelper.addPatient(patient);
            Toast.makeText(this, "Patient with ID " + patient.getPatientID() + " added succesfully!", Toast.LENGTH_SHORT).show();
            finish();
        }
        catch (DuplicateIDException e) {
            textViewErrorSubmitPatient.setText(e.getLocalizedMessage());
        }
    }

}
