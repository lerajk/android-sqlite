package com.alessioiannella_leeraj_comp304lab4.activities;

import android.content.Context;
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

    private LinearLayout linearLayourGetTestByID;
    private LinearLayout linearLayoutGetPatientByID;

    private EditText editTextTestID;
    private EditText editTextPatientID;

    private Button buttonAddTest;
    private Button buttonAddPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        linearLayourGetTestByID = (LinearLayout) findViewById(R.id.linearLayoutGetTestByID);
        linearLayoutGetPatientByID = (LinearLayout) findViewById(R.id.linearLayoutGetPatientByID);

        editTextTestID = (EditText) findViewById(R.id.editTextTestID);
        editTextPatientID = (EditText) findViewById(R.id.editTextPatientID);

        textViewHomeTitle = (TextView) findViewById(R.id.textViewHomeTitle);
        textViewErrorTestID = (TextView) findViewById(R.id.textViewErrorTestID);
        textViewErrorPatientID = (TextView) findViewById(R.id.textViewErrorPatientID);

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

    }

    public void handleOnClickGetTestByID(View view){
        if (editTextTestID.getText().toString().isEmpty()){
            textViewErrorTestID.setText("Please insert test ID");
            return;
        }


    }

    public void handleOnClickAddPatient(View view){

    }

    public void handleOnClickGetPatientByID(View view){

    }


}
