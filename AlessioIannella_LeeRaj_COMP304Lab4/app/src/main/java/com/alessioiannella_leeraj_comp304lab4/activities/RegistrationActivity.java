package com.alessioiannella_leeraj_comp304lab4.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.alessioiannella_leeraj_comp304lab4.R;

public class RegistrationActivity extends BaseActivity {

    private RadioButton radioButtonNurse;
    private RadioButton radioButtonDoctor;
    private EditText editTextID;
    private EditText

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void handleOnCLikcGoToLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void handleOnClickRegister(View view){



        this.dbHelper.getWritableDatabase().execSQL();
    }
}
