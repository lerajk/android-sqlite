package com.alessioiannella_leeraj_comp304lab4.models;

/**
 * Created by alessio on 27-Nov-17.
 */

public class Nurse {

    private String nurseID;
    private String firstName;
    private String lastName;
    private String department;
    private String password;

    public Nurse() {
    }

    public Nurse(String nurseID, String firstName, String lastName, String department, String password) {
        this.nurseID = nurseID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.password = password;
    }

    public String getNurseID() {
        return nurseID;
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

    public String getPassword() {
        return password;
    }

    public void setNurseID(String nurseID) {
        this.nurseID = nurseID;
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

    public void setPassword(String password) {
        this.password = password;
    }
}
