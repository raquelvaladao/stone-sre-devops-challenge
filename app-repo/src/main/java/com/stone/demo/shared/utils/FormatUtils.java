package com.stone.demo.shared.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class FormatUtils {

    private FormatUtils() {}

    public static final String DD_MM_YYYY = "dd/MM/yyyy";

    public static String dateToStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DD_MM_YYYY);
        return sdf.format(date);
    }

    public static Date strToDate(String dateString){
        SimpleDateFormat sdf = new SimpleDateFormat(DD_MM_YYYY);
        try {
            java.util.Date utilDate = sdf.parse(dateString);
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            return null;
        }
    }
}
