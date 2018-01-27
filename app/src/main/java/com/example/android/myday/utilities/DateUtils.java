package com.example.android.myday.utilities;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by fifiv on 15/01/2018.
 *
 * Class for handling date conversions
 */

public final class DateUtils {

    public static String getFriendlyDayString() {

        // Format unix time into human-readable yyyy-MM-dd HH:mm:ss
        long unixSeconds = System.currentTimeMillis();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");

        Date date = new Date(unixSeconds);

        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        String formattedDate = simpleDateFormat.format(date);

        return formattedDate;

    }

}
