package com.hospital.tools;



public class Statics {

    private static String username = "postgres";
    private static String password = "programeri";
    private static String usessl = "false";
    private static String url = "jdbc:postgresql://localhost:5432/hospital";

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUsessl() {
        return usessl;
    }

    public static String getUrl() {
        return url;
    }

}
