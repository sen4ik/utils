package org.sen4ik.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateUtil {

    public static String timeZone = "America/Los_Angeles";
    public static String MMddyyyy_hhmm_a_zzz = "MM/dd/yyyy hh:mm a zzz";
    public static String MMddyyyy_hhmm_a = "MM/dd/yyyy hh:mm a";
    public static String MMddyyyy_HHmm_zzz = "MM/dd/yyyy HH:mm zzz";
    public static String MMddyyyy_HHmm = "MM/dd/yyyy HH:mm";
    public static String yyyyMMdd_HHmmss = "yyyy/MM/dd HH:mm:ss";
    public static String MMddyyyy = "MM/dd/yyyy";
    public static String isoTimestamp = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static String formatDate(Date date, String dateFormat){
        log.info("CALLED: formatDate()");
        DateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(date);
    }

    public static String getDateAndTimeForFileName() {
        log.info("CALLED: getDateAndTimeForFileName()");
        String fn = getDateAndTime("yyyy-MM-dd_hh-mm");
        log.info("fn: " + fn);
        return fn;
    }

    public static String getDateAndTime() {
        log.info("CALLED: getDateAndTime()");
        return getDateAndTime(yyyyMMdd_HHmmss);
    }

    public static String getDateAndTime(String dateFormat) {
        log.info("CALLED: getDateAndTime()");
        DateFormat df = new SimpleDateFormat(dateFormat);
        Date date = new Date();
        return df.format(date);
    }

    public static String getDateAndTime(DateFormat df) {
        log.info("CALLED: getDateAndTime()");
        Date date = new Date();
        return df.format(date);
    }

}
