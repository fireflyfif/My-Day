package com.example.android.myapplication.data;

import android.provider.BaseColumns;

/**
 * Created by fifiv on 10/01/2018.
 */

public class WeatherContract {

    public static final class WeatherEntry implements BaseColumns {

        // Name of the weather table
        public static final String TABLE_NAME = "weather";

        // The date column
        public static final String COLUMN_DATE = "date";

        // Weather Id, used to identify the icon in the database
        public static final String COLUMN_WEATHER_ID = "weather_id";

        // Min temperature in Celsius for the day
        public static final String COLUMN_MIN_TEMP = "min";

        // Max temperature in Celsius for the day
        public static final String COLUMN_MAX_TEMP = "max";

        // Humidity for the day
        public static final String COLUMN_HUMIDITY = "humidity";

        // Pressure for the day
        public static final String COLUMN_PRESSURE = "pressure";

        // Wind speed for the day
        public static final String COLUMN_WIND_SPEED = "wind";

        // Meteorological degrees (e.g. 0 is north, 180 - south)
        public static final String COLUMN_DEGREES = "degrees";

    }

}
