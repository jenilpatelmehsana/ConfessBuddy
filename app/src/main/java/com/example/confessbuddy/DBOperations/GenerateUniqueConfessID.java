package com.example.confessbuddy.DBOperations;

import java.util.Date;

public class GenerateUniqueConfessID {

    public static String genereateUID(String email) {
        Date today = new Date();
        String time = String.valueOf(today.getTime());
//        TODO Encyption
        String uid = email + time;
        return uid;
    }

}
