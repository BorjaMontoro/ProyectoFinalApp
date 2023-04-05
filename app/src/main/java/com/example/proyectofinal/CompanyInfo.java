package com.example.proyectofinal;

import java.util.List;

public class CompanyInfo {
    private String phone;
    private List<String> mondayHours;
    private List<String> tuesdayHours;
    private List<String> wednesdayHours;
    private List<String> thursdayHours;
    private List<String> fridayHours;
    private List<String> saturdayHours;
    private List<String> sundayHours;

    // Constructor
    public CompanyInfo(String phone, List<String> mondayHours, List<String> tuesdayHours, List<String> wednesdayHours, List<String> thursdayHours, List<String> fridayHours, List<String> saturdayHours, List<String> sundayHours) {
        this.phone = phone;
        this.mondayHours = mondayHours;
        this.tuesdayHours = tuesdayHours;
        this.wednesdayHours = wednesdayHours;
        this.thursdayHours = thursdayHours;
        this.fridayHours = fridayHours;
        this.saturdayHours = saturdayHours;
        this.sundayHours = sundayHours;
    }

    // Getters y setters
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getMondayHours() {
        return mondayHours;
    }

    public void setMondayHours(List<String> mondayHours) {
        this.mondayHours = mondayHours;
    }

    public List<String> getTuesdayHours() {
        return tuesdayHours;
    }

    public void setTuesdayHours(List<String> tuesdayHours) {
        this.tuesdayHours = tuesdayHours;
    }

    public List<String> getWednesdayHours() {
        return wednesdayHours;
    }

    public void setWednesdayHours(List<String> wednesdayHours) {
        this.wednesdayHours = wednesdayHours;
    }

    public List<String> getThursdayHours() {
        return thursdayHours;
    }

    public void setThursdayHours(List<String> thursdayHours) {
        this.thursdayHours = thursdayHours;
    }

    public List<String> getFridayHours() {
        return fridayHours;
    }

    public void setFridayHours(List<String> fridayHours) {
        this.fridayHours = fridayHours;
    }

    public List<String> getSaturdayHours() {
        return saturdayHours;
    }

    public void setSaturdayHours(List<String> saturdayHours) {
        this.saturdayHours = saturdayHours;
    }

    public List<String> getSundayHours() {
        return sundayHours;
    }

    public void setSundayHours(List<String> sundayHours) {
        this.sundayHours = sundayHours;
    }
}

