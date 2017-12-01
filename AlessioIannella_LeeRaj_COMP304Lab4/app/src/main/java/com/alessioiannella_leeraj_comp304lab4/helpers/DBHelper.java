package com.alessioiannella_leeraj_comp304lab4.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.alessioiannella_leeraj_comp304lab4.exceptions.DoctorNotFoundException;
import com.alessioiannella_leeraj_comp304lab4.exceptions.DuplicateIDException;
import com.alessioiannella_leeraj_comp304lab4.exceptions.LoginFailedException;
import com.alessioiannella_leeraj_comp304lab4.exceptions.NurseNotFoundException;
import com.alessioiannella_leeraj_comp304lab4.exceptions.PatientNotFoundException;
import com.alessioiannella_leeraj_comp304lab4.exceptions.TestNotFoundException;
import com.alessioiannella_leeraj_comp304lab4.models.Doctor;
import com.alessioiannella_leeraj_comp304lab4.models.Nurse;
import com.alessioiannella_leeraj_comp304lab4.models.Patient;
import com.alessioiannella_leeraj_comp304lab4.models.Test;

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

    public void login(Context context, String id, String password) throws LoginFailedException{

        boolean isDoctor = false;

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

            isDoctor = true;
        }

        SharedPreferences sharedPref = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("id", cursor.getString(0));
        editor.putString("firstName", cursor.getString(1));
        editor.putString("lastName", cursor.getString(2));
        editor.putString("department", cursor.getString(3));
        editor.putBoolean("isDoctor", isDoctor);
        editor.commit();
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

    public void addNurse(Context context, Nurse nurse) throws DuplicateIDException{

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

        SharedPreferences sharedPref = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("id", nurse.getNurseID());
        editor.putString("firstName", nurse.getFirstName());
        editor.putString("lastName", nurse.getLastName());
        editor.putString("department", nurse.getDepartment());
        editor.putBoolean("isDoctor", false);
        editor.commit();
    }

    public void addDoctor(Context context, Doctor doctor) throws DuplicateIDException{

        sqLiteDatabase = getReadableDatabase();

        String[] columns = new String[]{ "doctorID" };
        String[] parameters = new String[]{ doctor.getDoctorID() };

        Cursor cursor = sqLiteDatabase.query("Doctor", columns, "doctorID=?", parameters, null, null, null, null);

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

        SharedPreferences sharedPref = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("id", doctor.getDoctorID());
        editor.putString("firstName", doctor.getFirstName());
        editor.putString("lastName", doctor.getLastName());
        editor.putString("department", doctor.getDepartment());
        editor.putBoolean("isDoctor", true);
        editor.commit();
    }

    public Test getTestByID(String id) throws TestNotFoundException{
        sqLiteDatabase = getReadableDatabase();

        String[] columns = new String[]{ "testID", "patientID", "nurseID", "bpl", "bph", "temperature" };
        String[] parameters = new String[]{ id };

        Cursor cursor = sqLiteDatabase.query("Test", columns, "testID=?", parameters, null, null, null, null);

        if (cursor == null || cursor.getCount() == 0){
            throw new TestNotFoundException("Test with ID " + id + " not found");
        }

        cursor.moveToFirst();

        Test test = new Test();
        test.setTestID(cursor.getString(0));
        test.setPatientID(cursor.getString(1));
        test.setNurseID(cursor.getString(2));
        test.setBpl(cursor.getDouble(3));
        test.setBph(cursor.getDouble(4));
        test.setTemperature(cursor.getDouble(5));

        return test;
    }

    public void addTest(Test test) throws DuplicateIDException, PatientNotFoundException{
        sqLiteDatabase = getReadableDatabase();

        String[] columns = new String[]{ "testID" };
        String[] parameters = new String[]{ test.getTestID() };

        Cursor cursor = sqLiteDatabase.query("Test", columns, "testID=?", parameters, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0){
            throw new DuplicateIDException("ID already exists!");
        }

        cursor.close();

        columns = new String[]{ "patientID" };
        parameters = new String[]{ test.getPatientID() };

        cursor = sqLiteDatabase.query("Patient", columns, "patientID=?", parameters, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0){
            throw new PatientNotFoundException("Patient with ID " + test.getPatientID() + " not found!");
        }

        sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("testID", test.getTestID());
        values.put("patientID", test.getPatientID());
        values.put("nurseID", test.getNurseID());
        values.put("bpl", test.getBpl());
        values.put("bph", test.getBph());
        values.put("temperature", test.getTemperature());

        sqLiteDatabase.insert("Test", null, values);
        sqLiteDatabase.close();
    }

    public Patient getPatientByID(String id) throws PatientNotFoundException{
        sqLiteDatabase = getReadableDatabase();

        String[] columns = new String[]{ "patientID", "firstName", "lastName", "department", "doctorID", "room" };
        String[] parameters = new String[]{ id };

        Cursor cursor = sqLiteDatabase.query("Patient", columns, "patientID=?", parameters, null, null, null, null);

        if (cursor == null || cursor.getCount() == 0){
            throw new PatientNotFoundException("Patient with ID " + id + " not found");
        }

        cursor.moveToFirst();

        Patient patient = new Patient();
        patient.setPatientID(cursor.getString(0));
        patient.setFirstName(cursor.getString(1));
        patient.setLastName(cursor.getString(2));
        patient.setDepartment(cursor.getString(3));
        patient.setDoctorID(cursor.getString(4));
        patient.setRoom(cursor.getString(5));

        return patient;
    }

    public void addPatient(Patient patient) throws DuplicateIDException{
        sqLiteDatabase = getReadableDatabase();

        String[] columns = new String[]{ "patientID" };
        String[] parameters = new String[]{ patient.getPatientID() };

        Cursor cursor = sqLiteDatabase.query("Patient", columns, "patientID=?", parameters, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0){
            throw new DuplicateIDException("ID already exists!");
        }

        sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("patientID", patient.getPatientID());
        values.put("firstName", patient.getFirstName());
        values.put("lastName", patient.getLastName());
        values.put("department", patient.getDepartment());
        values.put("doctorID", patient.getDoctorID());
        values.put("room", patient.getRoom());

        sqLiteDatabase.insert("Patient", null, values);
        sqLiteDatabase.close();
    }
}
