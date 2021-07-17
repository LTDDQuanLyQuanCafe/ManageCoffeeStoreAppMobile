package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common;

public class Helper {
    public static boolean isFullname(String str) {
        String expression = "^[a-zA-Z\\s]+";
        return str.matches(expression);
    }
    public static boolean isPhoneNumber(String str) {
        String expression = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";
        return str.matches(expression);
    }
}
