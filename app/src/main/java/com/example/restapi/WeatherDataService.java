package com.example.restapi;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class WeatherDataService {

    Context context;
    String cityID;

    public WeatherDataService(Context context){
        this.context = context;
    }

    public static final String QUERY_URL = "https://www.metaweather.com/api/location/search/?query=";

    public String getCityId(String cityName){
        String url = QUERY_URL + cityName;

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

                Toast.makeText(context, cityID, Toast.LENGTH_SHORT).show();
            }
        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);

        return cityID;
    }

//    public List<WeatherReportModel> getCityForecastById(String cityId){
//
//    }
//
//    public List<WeatherReportModel> getCityForecastByName(String cityName){
//
//    }

}
