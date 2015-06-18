package biz.globochem.sunshine.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by clebens on 6/13/15.
 */
public class WeatherDataParser {

    /**
     * Given a string of the form returned by the api call:
     * http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7
     * retrieve the maximum temperature for the day indicated by dayIndex
     * (Note: 0-indexed, so 0 would refer to the first day).
     */
    public static double getMaxTemperatureForDay(String weatherJsonStr, int dayIndex)
            throws JSONException {
        JSONObject weatherData = new JSONObject(weatherJsonStr);
        return weatherData.getJSONArray("list")
                .optJSONObject(dayIndex)
                .getJSONObject("temp")
                .getDouble("max");
    }

}
