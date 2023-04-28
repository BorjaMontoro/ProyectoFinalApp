package com.example.proyectofinal;

public class DateCompany {
    private String name;
    private String service;
    private String time;

    public DateCompany(String name, String service, String time) {
        this.name = name;
        this.service = service;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
