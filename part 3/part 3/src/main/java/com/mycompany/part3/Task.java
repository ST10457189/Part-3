package com.mycompany.part3;

public class Task {
    private String taskName;
    private String taskDescription;
    private String developerName;
    private int taskDuration;
    private String taskStatus;
    private String taskID;
    private static int totalTaskDuration = 0; 

    public Task(String taskName, String taskDescription, String developerName, int taskDuration, String taskStatus, int taskNumber) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerName = developerName;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = createTaskID(taskName, taskNumber, developerName);
        totalTaskDuration += taskDuration; 
    }

    public boolean checkTaskDescription() {
        return taskDescription.length() <= 50;
    }

    public String createTaskID(String taskName, int taskNumber, String developerName) {
        return taskName.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" + developerName.substring(developerName.length() - 3).toUpperCase();
    }

    public String printTaskDetails() {
        return "Task Status: " + taskStatus +
                "\nDeveloper Details: " + developerName +
                "\nTask Number: " + taskID.split(":")[1] + 
                "\nTask Name: " + taskName +
                "\nTask Description: " + taskDescription +
                "\nTask ID: " + taskID +
                "\nTask Duration: " + taskDuration + " hours";
    }

    public static int returnTotalHours() {
        return totalTaskDuration;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public int getTaskDuration() {
        return taskDuration;
    }

    public void setTaskDuration(int taskDuration) {
        this.taskDuration = taskDuration;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public static int getTotalTaskDuration() {
        return totalTaskDuration;
    }

    public static void setTotalTaskDuration(int totalTaskDuration) {
        Task.totalTaskDuration = totalTaskDuration;
    }
}
