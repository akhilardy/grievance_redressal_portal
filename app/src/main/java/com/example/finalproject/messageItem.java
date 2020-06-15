package com.example.finalproject;

public class messageItem {
    String username,location,problem,status;
    String problemCode;
    public String time;

    public messageItem(String username, String location, String problem, String status, String problemCode, String time) {
        this.username = username;
        this.location = location;
        this.problem = problem;
        this.status = status;
        this.problemCode = problemCode;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public String getLocation() {
        return location;
    }

    public String getProblem() {
        return problem;
    }

    public String getStatus() {
        return status;
    }

    public String getProblemCode() {
        return problemCode;
    }

    public String getTime() {
        return time;
    }
}
