package com.example.dontopen;

public final class Utils {
    private Utils(){}
    public static String capitalize(String s){
        if(s==null||s.length()<1){
            return s;
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
