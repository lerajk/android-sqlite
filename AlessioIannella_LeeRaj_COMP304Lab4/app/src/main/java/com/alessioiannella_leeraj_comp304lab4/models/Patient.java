package com.alessioiannella_leeraj_comp304lab4.models;

/**
 * Created by alessio on 27-Nov-17.
 */

public class Patient {

    private String patientID;
    private String firstName;
    private String lastName;
    private String department;
    private String doctorID;
    private String room;

    public Patient() {
    }

    public Patient(String patientID, String firstName, String lastName, String department, String doctorID, String room) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.doctorID = doctorID;
        this.room = room;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDepartment() {
        return department;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getRoom() {
        return room;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
