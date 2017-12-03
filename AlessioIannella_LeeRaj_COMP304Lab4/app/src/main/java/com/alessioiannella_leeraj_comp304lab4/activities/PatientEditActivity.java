package com.alessioiannella_leeraj_comp304lab4.activities;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alessioiannella_leeraj_comp304lab4.R;
import com.alessioiannella_leeraj_comp304lab4.exceptions.PatientNotFoundException;
import com.alessioiannella_leeraj_comp304lab4.helpers.DBHelper;
import com.alessioiannella_leeraj_comp304lab4.models.Patient;

import org.w3c.dom.Text;

public class PatientEditActivity extends AppCompatActivity {

    private EditText editTextPatientEditFirstName;
    private EditText editTextPatientEditLastName;
    private EditText editTextPatientEditDepartment;
    private EditText editTextPatientEditDoctorID;
    private EditText editTextPatientEditRoom;

    private TextView textViewErrorPatientEditFirstName;
    private TextView textViewErrorPatientEditLastName;
    private TextView textViewErrorPatientEditDepartment;
    private TextView textViewErrorPatientEditDoctorID;
    private TextView textViewErrorPatientEditRoom;
    private TextView textViewErrorUpdatePatient;

    private ConstraintLayout constraintLayoutProgressBarPatientEdit;

    private Patient patient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_edit);

        editTextPatientEditFirstName = (EditText) findViewById(R.id.editTextEditFirstName);
        editTextPatientEditLastName = (EditText) findViewById(R.id.editTextEditLastName);
        editTextPatientEditDepartment = (EditText) findViewById(R.id.editTextEditDepartment);
        editTextPatientEditDoctorID = (EditText) findViewById(R.id.editTextEditDoctorID);
        editTextPatientEditRoom = (EditText) findViewById(R.id.editTextEditRoom);

        textViewErrorPatientEditFirstName = (TextView) findViewById(R.id.textViewErrorEditFirstName);
        textViewErrorPatientEditLastName = (TextView) findViewById(R.id.textViewErrorEditLastName);
        textViewErrorPatientEditDepartment = (TextView) findViewById(R.id.textViewErrorEditDepartment);
        textViewErrorPatientEditDoctorID = (TextView) findViewById(R.id.textViewErrorEditDoctorID);
        textViewErrorPatientEditRoom = (TextView) findViewById(R.id.textViewErrorEditRoom);
        textViewErrorUpdatePatient = (TextView) findViewById(R.id.textViewErrorUpdatePatient);

        constraintLayoutProgressBarPatientEdit = (ConstraintLayout) findViewById(R.id.constraintLayoutProgressBarPatientEdit);

        String patientID = getIntent().getExtras().getString("patientID");

        try{
            DBHelper dbHelper = new DBHelper(this);

            patient = dbHelper.getPatientByID(patientID);

            editTextPatientEditFirstName.setText(patient.getFirstName());
            editTextPatientEditLastName.setText(patient.getLastName());
            editTextPatientEditDepartment.setText(patient.getDepartment());
            editTextPatientEditDoctorID.setText(patient.getDoctorID());
            editTextPatientEditRoom.setText(patient.getRoom());

            constraintLayoutProgressBarPatientEdit.setVisibility(View.GONE);
        }
        catch (PatientNotFoundException e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }


    }

    public void handleOnClickUpdatePatient(View view) {

        hideViews();

        boolean error = false;

        if (editTextPatientEditFirstName.getText().toString().isEmpty()){
            textViewErrorPatientEditFirstName.setText("Please insert patient first name");
            error = true;
        }
        if (editTextPatientEditLastName.getText().toString().isEmpty()){
            textViewErrorPatientEditLastName.setText("Please insert patient last name");
            error = true;
        }
        if (editTextPatientEditDepartment.getText().toString().isEmpty()){
            textViewErrorPatientEditDepartment.setText("Please insert patient department");
            error = true;
        }
        if (editTextPatientEditDoctorID.getText().toString().isEmpty()){
            textViewErrorPatientEditDoctorID.setText("Please insert patient doctor id");
            error = true;
        }
        if (editTextPatientEditRoom.getText().toString().isEmpty()){
            textViewErrorPatientEditRoom.setText("Please insert patient room");
            error = true;
        }

        if (!error){
            try{
                DBHelper dbHelper = new DBHelper(this);

                Patient updatedPatient = patient;
                updatedPatient.setFirstName(editTextPatientEditFirstName.getText().toString());
                updatedPatient.setLastName(editTextPatientEditLastName.getText().toString());
                updatedPatient.setDepartment(editTextPatientEditDepartment.getText().toString());
                updatedPatient.setDoctorID(editTextPatientEditDoctorID.getText().toString());
                updatedPatient.setRoom(editTextPatientEditRoom.getText().toString());

                dbHelper.updatePatient(updatedPatient);
                Toast.makeText(this, "Patient with id " + updatedPatient.getPatientID() + " updated successfully!", Toast.LENGTH_SHORT);
                finish();
            }
            catch (PatientNotFoundException e) {
                textViewErrorUpdatePatient.setText(e.getLocalizedMessage());
            }
        }
    }

    private void hideViews() {
        textViewErrorPatientEditFirstName.setText("");
        textViewErrorPatientEditLastName.setText("");
        textViewErrorPatientEditDepartment.setText("");
        textViewErrorPatientEditDoctorID.setText("");
        textViewErrorPatientEditRoom.setText("");
    }
}
