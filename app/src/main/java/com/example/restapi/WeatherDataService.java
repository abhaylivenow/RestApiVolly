package com.example.restapi;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {

    Context context;
    String cityID;

    public WeatherDataService(Context context){
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(String cityID);
    }

    public static final String QUERY_FOR_CITY_ID = "https://www.metaweather.com/api/location/search/?query=";
    public static final String QUERY_FOR_CITY_WEATHER_BY_ID = "https://www.metaweather.com/api/location/";

    public void getCityId(final String cityName , final VolleyResponseListener volleyResponseListener){
        String url = QUERY_FOR_CITY_ID + cityName;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                cityID = "";
                try {
                    JSONObject cityInfo = response.getJSONObject(0);
                    cityID = cityInfo.getString("woeid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Toast.makeText(context, cityID, Toast.LENGTH_SHORT).show();
                volleyResponseListener.onResponse(cityID);
            }
        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Something went wrong");
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);

//        return cityID;
    }

    public interface ForecastByIdResponse{
        void onError(String message);
        void onResponse(List<WeatherReportModel> weatherReportModel);
    }

    public void getCityForecastById(String cityId, final ForecastByIdResponse forecastByIdResponse){
        final List<WeatherReportModel> weatherReportModels = new ArrayList<>();
        String url = QUERY_FOR_CITY_WEATHER_BY_ID + cityId;

        // get the json object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();

                try {
                    JSONArray consolidateWeatherList = response.getJSONArray("consolidated_weather");

                    for(int i=0;i<consolidateWeatherList.length();i++) {

                        WeatherReportModel oneDayWeather = new WeatherReportModel();

                        JSONObject firstDayFromApi = (JSONObject) consolidateWeatherList.get(i);

                        oneDayWeather.setId(firstDayFromApi.getString("id"));
                        oneDayWeather.setWeather_state_name(firstDayFromApi.getString("weather_state_name"));
                        oneDayWeather.setWeather_state_abbr(firstDayFromApi.getString("weather_state_abbr"));
                        oneDayWeather.setWind_direction_compass(firstDayFromApi.getString("wind_direction_compass"));
                        oneDayWeather.setCreated(firstDayFromApi.getString("created"));
                        oneDayWeather.setApplicable_date(firstDayFromApi.getString("applicable_date"));

                        oneDayWeather.setMin_temp(firstDayFromApi.getLong("min_temp"));
                        oneDayWeather.setMax_temp(firstDayFromApi.getLong("max_temp"));
                        oneDayWeather.setWind_speed(firstDayFromApi.getLong("wind_speed"));
                        oneDayWeather.setWind_direction(firstDayFromApi.getLong("wind_direction"));
                        oneDayWeather.setAir_pressure(firstDayFromApi.getLong("air_pressure"));
                        oneDayWeather.setVisibility(firstDayFromApi.getLong("visibility"));

                        oneDayWeather.setHumidity(firstDayFromApi.getInt("humidity"));
                        oneDayWeather.setPredictability(firstDayFromApi.getInt("predictability"));

                        weatherReportModels.add(oneDayWeather);
                    }

                    forecastByIdResponse.onResponse(weatherReportModels);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
                // get the property of 'consolidate_weather' which is an array

                // get each item from array an assign it to the weatherReportModel

        MySingleton.getInstance(context).addToRequestQueue(request);
    }
//
//    public List<WeatherReportModel> getCityForecastByName(String cityName){
//
//    }

}
