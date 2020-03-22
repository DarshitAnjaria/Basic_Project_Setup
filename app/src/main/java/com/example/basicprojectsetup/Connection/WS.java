package com.example.basicprojectsetup.Connection;

public class WS {
    private static boolean isDeveloping = false;
    public static String baseUrl = isDeveloping ? "local url" : "live url";
}
