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
import com.alessioiannella_leeraj_comp304lab4.exceptions.PatientNotFoundException;
import com.alessioiannella_leeraj_comp304lab4.helpers.DBHelper;
import com.alessioiannella_leeraj_comp304lab4.models.Test;

public class TestActivity extends AppCompatActivity {

    private EditText editTextTestID;
    private EditText editTextPatientID;
    private EditText editTextBPL;
    private EditText editTextBPH;
    private EditText editTextTemperature;

    private TextView textViewErrorTestID;
    private TextView textViewErrorPatientID;
    private TextView textViewErrorBPL;
    private TextView textViewErrorBPH;
    private TextView textViewErrorTemperature;
    private TextView textViewErrorSubmitTest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        editTextTestID = (EditText) findViewById(R.id.editTextTestID);
        editTextPatientID = (EditText) findViewById(R.id.editTextPatientID);
        editTextBPL = (EditText) findViewById(R.id.editTextBPL);
        editTextBPH = (EditText) findViewById(R.id.editTextBPH);
        editTextTemperature = (EditText) findViewById(R.id.editTextTemperature);

        textViewErrorTestID = (TextView) findViewById(R.id.textViewErrorTestID);
        textViewErrorPatientID = (TextView) findViewById(R.id.textViewErrorPatientID);
        textViewErrorBPL = (TextView) findViewById(R.id.textViewErrorBPL);
        textViewErrorBPH = (TextView) findViewById(R.id.textViewErrorBPH);
        textViewErrorTemperature = (TextView) findViewById(R.id.textViewErrorTemperature);
        textViewErrorSubmitTest = (TextView) findViewById(R.id.textViewErrorSubmitTest);

    }

    public void handleOnClickSubmitTest(View view){
        if (editTextTestID.getText().toString().isEmpty()){
            textViewErrorTestID.setText("Please enter Test ID");
            return;
        }
        if (editTextPatientID.getText().toString().isEmpty()){
            textViewErrorPatientID.setText("Please enter Patient ID");
            return;
        }
        if (editTextBPL.getText().toString().isEmpty()){
            textViewErrorBPL.setText("Please enter BPL");
            return;
        }
        if (editTextBPH.getText().toString().isEmpty()){
            textViewErrorBPH.setText("Please enter BPH");
            return;
        }
        if (editTextTemperature.getText().toString().isEmpty()){
            textViewErrorTemperature.setText("Please enter Temperature");
            return;
        }

        SharedPreferences sharedPref = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String nurseID = sharedPref.getString("id", "N/A");

        try{
            float bpl = Float.parseFloat(editTextBPL.getText().toString());

            try{
                float bph = Float.parseFloat(editTextBPH.getText().toString());

                try{
                    float temperature = Float.parseFloat(editTextTemperature.getText().toString());

                    Test test = new Test();
                    test.setTestID(editTextTestID.getText().toString());
                    test.setPatientID(editTextPatientID.getText().toString());
                    test.setNurseID(nurseID);
                    test.setBpl(bpl);
                    test.setBph(bph);
                    test.setTemperature(temperature);

                    DBHelper dbHelper = new DBHelper(this);

                    dbHelper.addTest(test);
                    Toast.makeText(this, "Test " + editTextTestID.getText().toString() + " added succesfully!", Toast.LENGTH_SHORT).show();
                    finish();

                }
                catch (NumberFormatException e){
                    textViewErrorTemperature.setText("Temperature is not in the right format. Please enter a number");
                }
                catch (DuplicateIDException | PatientNotFoundException e) {
                    textViewErrorSubmitTest.setText(e.getLocalizedMessage());

                }
            }
            catch (NumberFormatException e){
                textViewErrorBPH.setText("BPH is not in the right format. Please enter a number");
            }
        }
        catch (NumberFormatException e){
            textViewErrorBPL.setText("BPL is not in the right format. Please enter a number");
        }

    }
}
