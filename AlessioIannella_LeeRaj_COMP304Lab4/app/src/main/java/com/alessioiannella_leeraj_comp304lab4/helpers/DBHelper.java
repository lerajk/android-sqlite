package com.alessioiannella_leeraj_comp304lab4.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HospitalDB";
    private static DBHelper instance = null;

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBHelper getInstance(Context context){

        if (instance == null ){
            instance = new DBHelper(context);
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sqlQuery = "CREATE TABLE Doctor (doctorID INT PRIMARY KEY, firstName TEXT NOT NULL, lastName TEXT NOT NULL, department TEXT NOT NULL, password TEXT NOT NULL);\n" +
                "CREATE TABLE Nurse (nurseID INT PRIMARY KEY, firstName TEXT NOT NULL, lastName TEXT NOT NULL, department TEXT NOT NULL, password TEXT NOT NULL);\n" +
                "CREATE TABLE Patient (patientID INT PRIMARY KEY, firstName TEXT NOT NULL, lastName TEXT NOT NULL, department TEXT NOT NULL, doctorID INT NOT NULL, room TEXT NOT NULL, FOREIGN KEY(doctorID) REFERENCES Doctor(doctorID));\n" +
                "CREATE TABLE Test (testID INT PRIMARY KEY, patientID INT NOT NULL, nurseID INT NOT NULL, bpl REAL NOT NULL, bph REAL NOT NULL, temperature REAL NOT NULL, FOREIGN KEY(patientID) REFERENCES Patient(patientID), FOREIGN KEY(nurseID) REFERENCES Nurse(nurseID));";

        sqLiteDatabase.execSQL(sqlQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
