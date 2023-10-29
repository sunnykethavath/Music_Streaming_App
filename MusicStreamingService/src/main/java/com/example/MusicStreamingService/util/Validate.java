package com.example.MusicStreamingService.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    public static final String Email_Regex = "^.+@admin\\.com$";
    public static boolean isValidUser(String email) {
        Pattern pattern = Pattern.compile(Email_Regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
