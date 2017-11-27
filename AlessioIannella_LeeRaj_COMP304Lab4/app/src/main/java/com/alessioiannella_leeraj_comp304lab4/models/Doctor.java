package com.alessioiannella_leeraj_comp304lab4.models;

/**
 * Created by alessio on 27-Nov-17.
 */

public class Doctor {

    private String doctorID;
    private String firstName;
    private String lastName;
    private String department;
    private String password;

    public Doctor() {
    }

    public Doctor(String doctorID, String firstName, String lastName, String department, String password) {
        this.doctorID = doctorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.password = password;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
