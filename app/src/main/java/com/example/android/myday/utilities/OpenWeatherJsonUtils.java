package com.example.android.myday.utilities;

import android.content.ContentValues;
import android.content.Context;

import com.example.android.myday.data.WeatherContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.util.Date;

/**
 * OpenWeatherMap JSON data
 */

public class OpenWeatherJsonUtils {

    /*
    Location information
     */
    private static final String OWM_CITY = "city";
    private static final String OWM_COORDINATES = "coord";

    /*
    Location coordinates
     */
    private static final String OWM_LATITUDE = "lat";
    private static final String OWM_LONGITUDE = "lon";

    /*
    Forecast information, where the "list" is an Array with all days
     */
    private static final String OWM_LIST = "list";
    private static final String OWM_DATE = "dt";

    private static final String OWM_PRESSURE = "pressure";
    private static final String OWM_HUMIDITY = "humidity";
    private static final String OWM_WIND_SPEED = "speed";
    private static final String OWM_WIND_DIRECTION = "deg";

    /*
    Forecast temperature for each day
     */
    private static final String OWM_TEMPERATURE = "temp";
    private static final String OWM_TEMPERATURE_MAX = "temp_max";
    private static final String OWM_TEMPERATURE_MIN = "temp_min";

    /*
    Weather Array holds description weather information for the day, like: "snow";
    Holds "id" for the weather (see what is that id for?)
    Holds also "icon" for the current weather (can use it later)
     */
    private static final String OWM_WEATHER = "weather";
    private static final String OWM_WEATHER_ID = "id";

    /*
    This is the HTTP code that the server is returning (e.g. if it's 200 - OK)
     */
    private static final String OWM_MESSAGE_CODE = "cod";


    public static ContentValues[] getWeatherValuesFromJson(Context context, String forecastJsonString)
        throws JSONException {

        JSONObject forecastJson = new JSONObject(forecastJsonString);

        /*
        Read from the JSON only if the server returns HTTP_OK message code = 200
         */
        if (forecastJson.has(OWM_MESSAGE_CODE)) {
            int errorCode = forecastJson.getInt(OWM_MESSAGE_CODE);

            switch (errorCode) {
                // Server responds with HTTP Status-Code 200: OK.
                case HttpURLConnection.HTTP_OK:
                    break;
                // Server responds with HTTP Status-Code 404: Not Found.
                case HttpURLConnection.HTTP_NOT_FOUND:
                    return null;
                default:
                    return null;
            }
        }

        JSONArray jsonWeatherArray = forecastJson.getJSONArray(OWM_LIST);

        JSONObject cityJson = forecastJson.getJSONObject(OWM_CITY);

        JSONObject cityCoord = cityJson.getJSONObject(OWM_COORDINATES);
        double cityLatitude = cityCoord.getDouble(OWM_LATITUDE);
        double cityLongitude = cityCoord.getDouble(OWM_LONGITUDE);

        // TODO Preferences by city

        ContentValues[] weatherContentValues = new ContentValues[jsonWeatherArray.length()];

        /*
        Read the date from the JSON, that is represented in Unix epoch time
        (e.g. 1515768100)
        TODO Format the weather information into human-readable format
         */
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        String date = dateFormat.format(new Date(cityJson.getLong(OWM_DATE)*1000));


        for (int i = 0; i < jsonWeatherArray.length(); i++) {

            String dateTime;
            double pressure;
            int humidity;
            double windSpeed;
            double windDirection;

            double highTemp;
            double lowTemp;

            int weatherId;

            /*
             Get the JSON object representing the day
            */
            JSONObject dayForecast = jsonWeatherArray.getJSONObject(i);

            // TODO Get the date in normalized format
            // !!! Not sure if it's working !!!
            dateTime = dayForecast.getString(date);

            pressure = dayForecast.getDouble(OWM_PRESSURE);
            humidity = dayForecast.getInt(OWM_HUMIDITY);
            windSpeed = dayForecast.getDouble(OWM_WIND_SPEED);
            windDirection = dayForecast.getDouble(OWM_WIND_DIRECTION);

            /*
            Description is an Array called "weather" within the object of the day
             */
            JSONObject weatherObject =
                    dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);

            weatherId = weatherObject.getInt(OWM_WEATHER_ID);

            /*
            Temperature are presented by the API in a child object called "temp"
             */
            JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);
            highTemp = temperatureObject.getDouble(OWM_TEMPERATURE_MAX);
            lowTemp = temperatureObject.getDouble(OWM_TEMPERATURE_MIN);
            
            ContentValues weatherValues = new ContentValues();
            // COMPLETED Add the date object here
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_DATE, dateTime);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_HUMIDITY, humidity);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_PRESSURE, pressure);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED, windSpeed);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_DEGREES, windDirection);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_MAX_TEMP,  highTemp);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_MIN_TEMP, lowTemp);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_WEATHER_ID, weatherId);
            
            weatherContentValues[i] = weatherValues;
            
        }
        
        return weatherContentValues;
    }
}
