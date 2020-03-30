package com.lk.wpw_android_project.Model;

public class ScheduleEventList {
    private String Time;
    private String Description;

    public ScheduleEventList() {
    }

    public ScheduleEventList(String time, String description) {
        Time = time;
        Description = description;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
