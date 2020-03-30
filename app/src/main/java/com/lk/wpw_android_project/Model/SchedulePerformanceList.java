package com.lk.wpw_android_project.Model;

public class SchedulePerformanceList {

    private String StartTime;
    private String EndTime;
    private String Description;

    public SchedulePerformanceList(String startTime, String endTime, String description) {
        StartTime = startTime;
        EndTime = endTime;
        Description = description;
    }

    public SchedulePerformanceList() {
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
