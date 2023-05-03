package com.example.proyectofinal;

public class DateCompany {
    private String name;
    private String service;
    private String time;
    private String timeFinish;

    public DateCompany(String name, String service, String time, String timeFinish) {
        this.name = name;
        this.service = service;
        this.time = time;
        this.timeFinish = timeFinish;
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

    public String getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(String timeFinish) {
        this.timeFinish = timeFinish;
    }
}
