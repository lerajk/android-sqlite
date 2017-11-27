package com.alessioiannella_leeraj_comp304lab4.models;

/**
 * Created by alessio on 27-Nov-17.
 */

public class Test {

    private String testID;
    private String patientID;
    private String nurseID;
    private double bpl;
    private double bph;
    private double temperature;

    public Test() {
    }

    public Test(String testID, String patientID, String nurseID, double bpl, double bph, double temperature) {
        this.testID = testID;
        this.patientID = patientID;
        this.nurseID = nurseID;
        this.bpl = bpl;
        this.bph = bph;
        this.temperature = temperature;
    }

    public String getTestID() {
        return testID;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getNurseID() {
        return nurseID;
    }

    public double getBpl() {
        return bpl;
    }

    public double getBph() {
        return bph;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTestID(String testID) {
        this.testID = testID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void setNurseID(String nurseID) {
        this.nurseID = nurseID;
    }

    public void setBpl(double bpl) {
        this.bpl = bpl;
    }

    public void setBph(double bph) {
        this.bph = bph;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
