package com.alessioiannella_leeraj_comp304lab4.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.alessioiannella_leeraj_comp304lab4.R;
import com.alessioiannella_leeraj_comp304lab4.helpers.DBHelper;

public class MainActivity extends BaseActivity {

    private ProgressBar progressBarLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBarLoading = (ProgressBar) findViewById(R.id.progressBarLoading);

        this.dbHelper = DBHelper.getInstance(this);

        progressBarLoading.setVisibility(View.GONE);

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);

    }
}
