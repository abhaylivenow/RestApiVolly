package com.example.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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
    }
}