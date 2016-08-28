package com.ninemax.utils;

import java.util.UUID;

/**
 * Created by Pual on 2016/8/27.
 */
public class Tools {

    public static String getLowerCaseUUID32(){
        return UUID.randomUUID().toString();
    }

    public static String getUpperCaseUUID32() {
        return getLowerCaseUUID32().toUpperCase();
    }

    public static String getLowerCaseUUID32NoBlank() {
        return getLowerCaseUUID32().replace("-", "");
    }

    public static String getUpperCaseUUID32NoBlank() {
        return getUpperCaseUUID32().replace("-", "");
    }

    public static String getLowerCaseUUID16() {
        return getLowerCaseUUID32().substring(0, 16);
    }

    public static String getLowerCaseUUIDNoBlank() {
        return getLowerCaseUUIDNoBlank().substring(0, 16);
    }

    public static String getUpperCaseUUID16() {
        return getUpperCaseUUID32().substring(0, 16);
    }

    public static String getUpperCaseUUID16NoBlank() {
        return getUpperCaseUUID32NoBlank().substring(0, 16);
    }

    public static String getLowerCaseUUID8() {
        return getLowerCaseUUID32().substring(0, 8);
    }

    public static String getLowerCaseUUID8NoBlank() {
        return getLowerCaseUUID32().replace("-", "").substring(0, 8);
    }

    public static String getUpperCaseUUID8() {
        return getUpperCaseUUID32().substring(0, 8);
    }

    public static String getUpperCaseUUID8NoBlank() {
        return getUpperCaseUUID32().replace("-", "").substring(0, 8);
    }
}
