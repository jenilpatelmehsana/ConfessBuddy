package com.example.confessbuddy.Model;

import java.util.Comparator;

//TODO
public class Confess {

    String confess;
    String confessDate;
    String university;
    String city;
    String confessID;
    Long sortingForTime;

    public Confess() {

    }

    public Confess(String confess, String city, String university, String confessDate) {
        this.confess = confess;
        this.confessDate = confessDate;
        this.university = university;
        this.city = city;
    }

    public Confess(String confess, String city, String university, String confessDate, String confessID) {
        this.confess = confess;
        this.confessDate = confessDate;
        this.university = university;
        this.city = city;
        this.confessID = confessID;
    }

//    getter and settter

    public String getConfess() {
        return confess;
    }

    public void setConfess(String confess) {
        this.confess = confess;
    }

    public String getConfessDate() {
        return confessDate;
    }

    public void setConfessDate(String confessDate) {
        this.confessDate = confessDate;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getConfessID() {
        return confessID;
    }

    public void setConfessID(String confessID) {
        this.confessID = confessID;
    }

    public Long getSortingForTime() {
        return sortingForTime;
    }

    public void setSortingForTime(Long sortingForTime) {
        this.sortingForTime = sortingForTime;
    }

    public static class ConfessComparator implements Comparator<Confess> {

        @Override
        public int compare(Confess a, Confess b) {
            return b.getSortingForTime().compareTo(a.getSortingForTime());
        }
    }
}
