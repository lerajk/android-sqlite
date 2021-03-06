package com.alessioiannella_leeraj_comp304lab4.activities;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alessioiannella_leeraj_comp304lab4.R;
import com.alessioiannella_leeraj_comp304lab4.exceptions.NurseNotFoundException;
import com.alessioiannella_leeraj_comp304lab4.exceptions.PatientNotFoundException;
import com.alessioiannella_leeraj_comp304lab4.exceptions.TestNotFoundException;
import com.alessioiannella_leeraj_comp304lab4.helpers.DBHelper;
import com.alessioiannella_leeraj_comp304lab4.models.Nurse;
import com.alessioiannella_leeraj_comp304lab4.models.Patient;
import com.alessioiannella_leeraj_comp304lab4.models.Test;

import org.w3c.dom.Text;

public class TestDetailActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayoutProgressBar;

    private TextView textViewTestDetailID;
    private TextView textViewTestDetailPatient;
    private TextView textViewTestDetailNurse;
    private TextView textViewTestDetailBPL;
    private TextView textViewTestDetailBPH;
    private TextView textViewTestDetailTemperature;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_detail);

        textViewTestDetailID = (TextView) findViewById(R.id.textViewTestDetailID);
        textViewTestDetailPatient = (TextView) findViewById(R.id.textViewTestDetailPatient);
        textViewTestDetailNurse = (TextView) findViewById(R.id.textViewTestDetailNurse);
        textViewTestDetailBPL = (TextView) findViewById(R.id.textViewTestDetailBPL);
        textViewTestDetailBPH = (TextView) findViewById(R.id.textViewTestDetailBPH);
        textViewTestDetailTemperature = (TextView) findViewById(R.id.textViewTestDetailTemperature);

        constraintLayoutProgressBar = (ConstraintLayout) findViewById(R.id.constraintLayoutProgressBar);

        String testID = this.getIntent().getExtras().getString("testID");

        DBHelper dbHelper = new DBHelper(this);

        try{
            Test test = dbHelper.getTestByID(testID);
            Patient patient = dbHelper.getPatientByID(test.getPatientID());
            Nurse nurse = dbHelper.getNurse(test.getNurseID());

            textViewTestDetailID.setText("Test ID: " + test.getTestID());
            textViewTestDetailNurse.setText("Created by the nurse: " + nurse.getFirstName() + " " + nurse.getLastName());
            textViewTestDetailPatient.setText("On patient: " + patient.getFirstName() + " " + patient.getLastName());
            textViewTestDetailBPH.setText("BPH Value: " + test.getBph());
            textViewTestDetailBPL.setText("BPL Value: " + test.getBpl());
            textViewTestDetailTemperature.setText("Temperature value: " + test.getTemperature());

            constraintLayoutProgressBar.setVisibility(View.GONE);
        }
        catch (TestNotFoundException | PatientNotFoundException | NurseNotFoundException e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
