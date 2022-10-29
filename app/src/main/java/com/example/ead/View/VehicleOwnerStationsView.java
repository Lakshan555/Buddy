package com.example.ead.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ead.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VehicleOwnerStationsView extends AppCompatActivity {

    String URL = "https://pasindu-fuelapi.herokuapp.com/fuelStations/getAll";

    private RecyclerView recyclerView;
    List<Station> stationList;
    StationAdapter stationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_owner_stations_view);

        recyclerView = findViewById(R.id.recycerlviewStations);
        stationList = new ArrayList<>();
        displayStation();

    }

    private void displayStation() {

//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        stationAdapter = new StationAdapter(getApplicationContext(), stationList);
//        recyclerView.setAdapter(stationAdapter);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Stations", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("fuelStations");

                    if(jsonArray.length() > 0){

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject stationObg = jsonArray.getJSONObject(i);

                            Log.d("station", "Response obg " + stationObg);
                            Station station = new Station();
                            station.setOwnerId(stationObg.getString("ownerId").toString());
                            station.setRegisterNo(stationObg.getString("registerNo").toString());
                            station.setName(stationObg.getString("name").toString());
                            station.setStationNo(stationObg.getString("_id").toString());

                            stationList.add(station);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                stationAdapter = new StationAdapter(VehicleOwnerStationsView.this, stationList);
                recyclerView.setAdapter(stationAdapter);
                Toast.makeText(VehicleOwnerStationsView.this, "Success", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

//        Toast.makeText(VownerStationView.this, "Success", Toast.LENGTH_SHORT).show();

    }
}