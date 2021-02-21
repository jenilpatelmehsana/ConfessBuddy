package com.example.confessbuddy.Model;

import org.json.JSONException;
import org.json.JSONObject;


public class User {

    String userID;
    String userEmail;
    String university;
    String city;
    int confessCount = 0;

    public User() { }

    public User(String userEmail) {
        this.userEmail = userEmail;
    }



    public static User objectToUser(Object obj) {
        User ans = new User();

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(obj.toString());
            ans.setUserEmail(jsonObject.getString("userEmail"));
            ans.setUserID(jsonObject.getString("userID"));
            ans.setUniversity(jsonObject.getString("university"));
            ans.setConfessCount(jsonObject.getInt("confessCount"));
            ans.setCity(jsonObject.getString("city"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return ans;
    }

    public static boolean perfectUser(User user) {
        if(user.getCity() == null ||
                user.getUserEmail() == null ||
                user.getUniversity() == null ||
                user.getUserID() == null
        ) return false;
        return true;
    }

    //unused methods
    public static JSONObject convertToJson(User user) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("userEmail", user.getUserEmail());
            obj.put("confessCount", user.getConfessCount());
            obj.put("city", user.getCity());
            obj.put("university", user.getUniversity());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static User convertToUser(JSONObject obj) {
        User user = null;
        try {
            user = new User((String)obj.get("userEmail"));
            user.setCity((String)obj.get("city"));
            user.setUniversity((String)obj.get("university"));
            user.setConfessCount(obj.getInt("confessCount"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

//    getter and setter


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getConfessCount() {
        return confessCount;
    }

    public void setConfessCount(int confessCount) {
        this.confessCount = confessCount;
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
