package com.alessioiannella_leeraj_comp304lab4.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.alessioiannella_leeraj_comp304lab4.R;

public class TestActivity extends AppCompatActivity {

    private EditText editTextTestID;
    private EditText editTextPatientID;
    private EditText editTextNurseID;
    private EditText editTextBPL;
    private EditText editTextBPH;
    private EditText editTextTemperature;

    private TextView textViewErrorTestID;
    private TextView textViewErrorPatientID;
    private TextView textViewErrorNurseID;
    private TextView textViewErrorBPL;
    private TextView textViewErrorBPH;
    private TextView textViewErrorTemperature;
    private TextView textViewErrorSubmitTest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        editTextTestID = (EditText) findViewById(R.id.editTextTestID);
        editTextNurseID = (EditText) findViewById(R.id.editTextNurseID);
        editTextPatientID = (EditText) findViewById(R.id.editTextPatientID);
        editTextBPL = (EditText) findViewById(R.id.editTextBPL);
        editTextBPH = (EditText) findViewById(R.id.editTextBPH);
        editTextTemperature = (EditText) findViewById(R.id.editTextTemperature);

        textViewErrorTestID = (TextView) findViewById(R.id.textViewErrorTestID);
        textViewErrorNurseID = (TextView) findViewById(R.id.textViewErrorNurseID);
        textViewErrorPatientID = (TextView) findViewById(R.id.textViewErrorPatientID);
        textViewErrorBPL = (TextView) findViewById(R.id.textViewErrorBPL);
        textViewErrorBPH = (TextView) findViewById(R.id.textViewErrorBPH);
        textViewErrorTemperature = (TextView) findViewById(R.id.textViewErrorTemperature);
        textViewErrorSubmitTest = (TextView) findViewById(R.id.textViewErrorSubmitTest);

    }
}
