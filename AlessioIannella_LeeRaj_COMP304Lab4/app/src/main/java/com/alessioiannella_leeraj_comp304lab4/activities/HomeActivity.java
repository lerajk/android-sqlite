package com.alessioiannella_leeraj_comp304lab4.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alessioiannella_leeraj_comp304lab4.R;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {

    private TextView textViewHomeTitle;
    private TextView textViewErrorTestID;
    private TextView textViewErrorPatientID;

    private EditText editTextTestID;
    private EditText editTextPatientID;

    private Button buttonAddTest;
    private Button buttonAddPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        editTextTestID = (EditText) findViewById(R.id.editTextTestID);
        editTextPatientID = (EditText) findViewById(R.id.editTextPatientID);

        textViewHomeTitle = (TextView) findViewById(R.id.textViewHomeTitle);
        textViewErrorTestID = (TextView) findViewById(R.id.textViewErrorTestID);
        textViewErrorPatientID = (TextView) findViewById(R.id.textViewErrorPatientID);

        buttonAddTest = (Button) findViewById(R.id.buttonAddTest);
        buttonAddPatient = (Button) findViewById(R.id.buttonAddPatient);

        SharedPreferences sharedPref = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String firstName = sharedPref.getString("firstName", "N/A");
        String lastName = sharedPref.getString("lastName", "N/A");
        boolean isDoctor = sharedPref.getBoolean("isDoctor", false);

        textViewHomeTitle.setText("Welcome " + firstName + " " + lastName + "!");

        if (isDoctor){
            buttonAddTest.setVisibility(View.GONE);
        }
        else{
            buttonAddPatient.setVisibility(View.GONE);
        }
    }

    public void handleOnClickAddTest(View view){
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    public void handleOnClickGetTestByID(View view){
        if (editTextTestID.getText().toString().isEmpty()){
            textViewErrorTestID.setText("Please insert test ID");
            return;
        }

        Intent intent = new Intent(this, TestDetailActivity.class);
        intent.putExtra("testID", editTextTestID.getText().toString());
        startActivity(intent);
    }

    public void handleOnClickAddPatient(View view){

        Intent intent = new Intent(this, PatientActivity.class);
        startActivity(intent);

    }

    public void handleOnClickGetPatientByID(View view){
        if (editTextPatientID.getText().toString().isEmpty()){
            textViewErrorPatientID.setText("Please insert patient ID");
            return;
        }

        Intent intent = new Intent(this, PatientDetailActivity.class);
        intent.putExtra("patientID", editTextPatientID.getText().toString());
        startActivity(intent);
    }

    public void handleOnClickLogout(View view){
        SharedPreferences sharedPref = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
    }


}
