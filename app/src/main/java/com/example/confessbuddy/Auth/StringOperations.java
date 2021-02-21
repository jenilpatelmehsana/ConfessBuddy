package com.example.confessbuddy.Auth;

public class StringOperations {

    public static String emailToUserID(String email) {
        String[] part1 = email.split("@");
        String[] part2 = new String[2];
        if(part1.length > 1)
            part2 = part1[1].split("\\.",2);
        String ans = "";
        if(part1.length > 0) ans += part1[0];
        if(part2.length > 0) ans += part2[0];
        if(part2.length > 1) ans += part2[1];

//        for(String x: part1) {
//            System.out.print(x + ' ');
//        }
//
//        for(String x : part2) {
//            System.out.print(x + ' ');
//        }

        System.out.println(ans);

        return ans;
    }

}
