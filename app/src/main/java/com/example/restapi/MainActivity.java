package com.example.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button btnGetCityId, btnGetWeatherById, btnGetWeatherByName;
    private EditText edtEnterDetail;
    private ListView listViewDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetCityId = findViewById(R.id.btnGetCityId);
        btnGetWeatherById = findViewById(R.id.btnWeatherByCityId);
        btnGetWeatherByName = findViewById(R.id.btnWeatherCityByName);

        edtEnterDetail = findViewById(R.id.edt_enterDetails);
        listViewDetails = findViewById(R.id.listViewDetails);

        btnGetCityId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);
                String cityId = weatherDataService.getCityId(edtEnterDetail.getText().toString());

                Toast.makeText(MainActivity.this, cityId, Toast.LENGTH_SHORT).show();
            }
        });

        btnGetWeatherById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnGetWeatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}