package com.alessioiannella_leeraj_comp304lab4.activities;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alessioiannella_leeraj_comp304lab4.R;
import com.alessioiannella_leeraj_comp304lab4.exceptions.DoctorNotFoundException;
import com.alessioiannella_leeraj_comp304lab4.exceptions.PatientNotFoundException;
import com.alessioiannella_leeraj_comp304lab4.helpers.DBHelper;
import com.alessioiannella_leeraj_comp304lab4.models.Doctor;
import com.alessioiannella_leeraj_comp304lab4.models.Patient;

import org.w3c.dom.Text;

public class PatientDetailActivity extends AppCompatActivity {

    private TextView textViewPatientDetailPatient;
    private TextView textViewPatientDetailDoctor;
    private TextView textViewPatientDetailDepartment;
    private TextView textViewPatientDetailRoom;

    private ConstraintLayout constraintLayoutProgressBarPatientDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);

        textViewPatientDetailPatient = (TextView) findViewById(R.id.textViewPatientDetailPatient);
        textViewPatientDetailDoctor = (TextView) findViewById(R.id.textViewPatientDetailDoctor);
        textViewPatientDetailDepartment = (TextView) findViewById(R.id.textViewPatientDetailDepartment);
        textViewPatientDetailRoom = (TextView) findViewById(R.id.textViewPatientDetailRoom);

        constraintLayoutProgressBarPatientDetail = (ConstraintLayout) findViewById(R.id.constraintLayoutProgressBarPatientDetail);

        String patientID = getIntent().getExtras().getString("patientID");

        try{
            DBHelper dbHelper = new DBHelper(this);
            Patient patient = dbHelper.getPatientByID(patientID);
            Doctor doctor = dbHelper.getDoctor(patient.getDoctorID());

            textViewPatientDetailPatient.setText("Patient " + patient.getPatientID() + ": " + patient.getFirstName() + " " + patient.getLastName());
            textViewPatientDetailDoctor.setText("Followed by: Dr. " + doctor.getFirstName() + " " + doctor.getLastName());
            textViewPatientDetailDepartment.setText("Department: " + patient.getDepartment());
            textViewPatientDetailRoom.setText("Room: " + patient.getRoom());

            constraintLayoutProgressBarPatientDetail.setVisibility(View.GONE);
        }
        catch (PatientNotFoundException | DoctorNotFoundException e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    public void handleOnClickEditPatientInfo(View view) {
        Intent intent = new Intent(this, PatientEditActivity.class);
        intent.putExtra("patientID", getIntent().getExtras().getString("patientID"));
        startActivity(intent);
    }

    @Override
    protected void onResume() {

        constraintLayoutProgressBarPatientDetail.setVisibility(View.VISIBLE);

        String patientID = getIntent().getExtras().getString("patientID");

        try{
            DBHelper dbHelper = new DBHelper(this);
            Patient patient = dbHelper.getPatientByID(patientID);
            Doctor doctor = dbHelper.getDoctor(patient.getDoctorID());

            textViewPatientDetailPatient.setText("Patient " + patient.getPatientID() + ": " + patient.getFirstName() + " " + patient.getLastName());
            textViewPatientDetailDoctor.setText("Followed by: Dr. " + doctor.getFirstName() + " " + doctor.getLastName());
            textViewPatientDetailDepartment.setText("Department: " + patient.getDepartment());
            textViewPatientDetailRoom.setText("Room: " + patient.getRoom());

            constraintLayoutProgressBarPatientDetail.setVisibility(View.GONE);
        }
        catch (PatientNotFoundException | DoctorNotFoundException e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }

        super.onResume();
    }
}
