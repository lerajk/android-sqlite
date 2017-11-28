package com.alessioiannella_leeraj_comp304lab4.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.alessioiannella_leeraj_comp304lab4.exceptions.DoctorNotFoundException;
import com.alessioiannella_leeraj_comp304lab4.exceptions.DuplicateIDException;
import com.alessioiannella_leeraj_comp304lab4.exceptions.LoginFailedException;
import com.alessioiannella_leeraj_comp304lab4.exceptions.NurseNotFoundException;
import com.alessioiannella_leeraj_comp304lab4.models.Doctor;
import com.alessioiannella_leeraj_comp304lab4.models.Nurse;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HospitalDB";
    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        this.sqLiteDatabase = sqLiteDatabase;

        String sqlQuery = "CREATE TABLE Doctor (doctorID TEXT PRIMARY KEY, firstName TEXT NOT NULL, lastName TEXT NOT NULL, department TEXT NOT NULL, password TEXT NOT NULL);";

        this.sqLiteDatabase.execSQL(sqlQuery);

        sqlQuery = "CREATE TABLE Nurse (nurseID TEXT PRIMARY KEY, firstName TEXT NOT NULL, lastName TEXT NOT NULL, department TEXT NOT NULL, password TEXT NOT NULL);";

        this.sqLiteDatabase.execSQL(sqlQuery);

        sqlQuery = "CREATE TABLE Patient (patientID TEXT PRIMARY KEY, firstName TEXT NOT NULL, lastName TEXT NOT NULL, department TEXT NOT NULL, doctorID TEXT NOT NULL, room TEXT NOT NULL, FOREIGN KEY(doctorID) REFERENCES Doctor(doctorID));";

        this.sqLiteDatabase.execSQL(sqlQuery);

        sqlQuery = "CREATE TABLE Test (testID TEXT PRIMARY KEY, patientID TEXT NOT NULL, nurseID TEXT NOT NULL, bpl REAL NOT NULL, bph REAL NOT NULL, temperature REAL NOT NULL, FOREIGN KEY(patientID) REFERENCES Patient(patientID), FOREIGN KEY(nurseID) REFERENCES Nurse(nurseID));";

        this.sqLiteDatabase.execSQL(sqlQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void login(String id, String password) throws LoginFailedException{

        sqLiteDatabase = getReadableDatabase();

        String[] columns = new String[]{ "nurseID", "firstName", "lastName", "department", "password"};
        String[] parameters = new String[]{ id, password };

        Cursor cursor = sqLiteDatabase.query("Nurse", columns, "nurseID=? AND password=?", parameters, null, null, null, null);

        if (cursor == null || cursor.getCount() == 0){

            columns = new String[]{ "doctorID", "firstName", "lastName", "department", "password"};

            cursor = sqLiteDatabase.query("Doctor", columns, "doctorID=? AND password=?", parameters, null, null, null, null);

            if (cursor == null || cursor.getCount() == 0){
                throw new LoginFailedException("Wrong Id/password. Please try again");
            }
        }

        // SAVE LOGIN INFO IN PREFERENCES
    }

    public Nurse getNurse(String nurseID) throws NurseNotFoundException {

        sqLiteDatabase = getReadableDatabase();

        String[] columns = new String[]{ "nurseID", "firstName", "lastName", "department", "password"};
        String[] parameters = new String[]{ nurseID };

        Cursor cursor = sqLiteDatabase.query("Nurse", columns , "nurseID=?", parameters, null, null, null, null);

        if (cursor == null || cursor.getCount() == 0){
            throw new NurseNotFoundException("Nurse with ID + " + nurseID + " not found");
        }

        cursor.moveToFirst();

        Nurse nurse = new Nurse();
        nurse.setNurseID(cursor.getString(0));
        nurse.setFirstName(cursor.getString(1));
        nurse.setLastName(cursor.getString(2));
        nurse.setDepartment(cursor.getString(3));
        nurse.setPassword(cursor.getString(4));

        return nurse;
    }

    public Doctor getDoctor(String doctorID) throws DoctorNotFoundException{

        sqLiteDatabase = getReadableDatabase();

        String[] columns = new String[]{ "doctorID", "firstName", "lastName", "department", "password"};
        String[] parameters = new String[]{ doctorID };

        Cursor cursor = sqLiteDatabase.query("Doctor", columns, "doctorID=?", parameters, null, null, null, null);

        if (cursor == null || cursor.getCount() == 0){
            throw new DoctorNotFoundException("Doctor with ID + " + doctorID + " not found");
        }

        cursor.moveToFirst();

        Doctor doctor = new Doctor();
        doctor.setDoctorID(cursor.getString(0));
        doctor.setFirstName(cursor.getString(1));
        doctor.setLastName(cursor.getString(2));
        doctor.setDepartment(cursor.getString(3));
        doctor.setPassword(cursor.getString(4));

        return doctor;
    }

    public void addNurse(Nurse nurse) throws DuplicateIDException{

        sqLiteDatabase = getReadableDatabase();

        String[] columns = new String[]{ "nurseID" };
        String[] parameters = new String[]{ nurse.getNurseID() };

        Cursor cursor = sqLiteDatabase.query("Nurse", columns, "nurseID=?", parameters, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0){
            throw new DuplicateIDException("ID already exists!");
        }

        sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nurseID", nurse.getNurseID());
        values.put("firstName", nurse.getFirstName());
        values.put("lastName", nurse.getLastName());
        values.put("department", nurse.getDepartment());
        values.put("password", nurse.getPassword());

        sqLiteDatabase.insert("Nurse", null, values);
        sqLiteDatabase.close();
    }

    public void addDoctor(Doctor doctor) throws DuplicateIDException{

        sqLiteDatabase = getReadableDatabase();

        String[] columns = new String[]{ "doctorID" };
        String[] parameters = new String[]{ doctor.getDoctorID() };

        Cursor cursor = sqLiteDatabase.query("Doctor", columns, "nurseID=?", parameters, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0){
            throw new DuplicateIDException("ID already exists!");
        }

        sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nurseID", doctor.getDoctorID());
        values.put("firstName", doctor.getFirstName());
        values.put("lastName", doctor.getLastName());
        values.put("department", doctor.getDepartment());
        values.put("password", doctor.getPassword());

        sqLiteDatabase.insert("Doctor", null, values);
        sqLiteDatabase.close();
    }
}
