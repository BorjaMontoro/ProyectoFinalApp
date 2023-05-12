package com.example.proyectofinal;

public class DateClient {
    private String id;
    private String service;
    private String company;
    private String month;
    private String day;
    private String time;
    private String year;

    public DateClient(String id, String service, String company, String month, String day, String time, String year) {
        this.id = id;
        this.service = service;
        this.company = company;
        this.month = month;
        this.day = day;
        this.time = time;
        this.year = year;
    }

    public String getService() {
        return service;
    }

    public String getCompany() {
        return company;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getYear(){
        return year;
    }

    public String getId() {
        return id;
    }
}

