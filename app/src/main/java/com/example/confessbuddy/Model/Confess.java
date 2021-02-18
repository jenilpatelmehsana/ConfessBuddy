package com.example.confessbuddy.Model;

import java.util.Date;

//TODO
public class Confess {

    String confess;
    Date confessDate;
    String university;

    public Confess(String confess, Date confessDate, String university) {
        this.confess = confess;
        this.confessDate = confessDate;
        this.university = university;
    }

//    getter and settter

    public String getConfess() {
        return confess;
    }

    public void setConfess(String confess) {
        this.confess = confess;
    }

    public Date getConfessDate() {
        return confessDate;
    }

    public void setConfessDate(Date confessDate) {
        this.confessDate = confessDate;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
