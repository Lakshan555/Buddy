package com.example.ead.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ead.Home.HomeStationOwner;
import com.example.ead.Home.HomeVehicleOwner;
import com.example.ead.InputFuelStatus;
import com.example.ead.R;
import com.example.ead.UpdateFuelStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class FuelDetailsVehicleOwnerView extends AppCompatActivity {
    private TextView dVOValue,sdVOValue,p92VOValue,p95VOValue;
    private Button vOArrivedBtn,vODpepaBtn;
    private RequestQueue queue;
    private String  d,sd,p92,p95;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_detailsdriver_view);

        dVOValue = (TextView) findViewById(R.id.dVOValue);
        sdVOValue = (TextView) findViewById(R.id.sdVOValue);
        p92VOValue = (TextView) findViewById(R.id.p92VOValue);
        p95VOValue = (TextView) findViewById(R.id.p95VOValue);
        vOArrivedBtn = findViewById(R.id.vOArrivedBtn);
        vODpepaBtn = findViewById(R.id.vODpepaBtn);

        //get details from api
        getDetails();

        vOArrivedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FuelDetailsVehicleOwnerView.this, InputFuelStatus.class);

                startActivity(intent);


            }
        });

        vODpepaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FuelDetailsVehicleOwnerView.this, HomeVehicleOwner.class);
                startActivity(intent);
            }
        });

    }

    //get updated details
    private void getDetails() {

        String id = "635ac78a23d441bc8ef83db2";
        queue = Volley.newRequestQueue(this);
        String url = "https://ishankafuel.herokuapp.com/fuel_stations/"+id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (response.get("isSuccessful").equals(true)) {


                                JSONObject stations = response.getJSONObject("fuelStations");
                                JSONArray fd = stations.getJSONArray("fuel_details");

                                d = fd.getJSONObject(0).getString("quantity");
                                sd = fd.getJSONObject(1).getString("quantity");
                                p92 = fd.getJSONObject(2).getString("quantity");
                                p95 = fd.getJSONObject(3).getString("quantity");

                                dVOValue.setText(d+"L");
                                sdVOValue.setText(sd+"L");
                                p92VOValue.setText(p92+"L");
                                p95VOValue.setText(p95+"L");

                                Log.e("Success", response.toString());
                                Log.e("Success", fd.getString(0) );

                            } else {
                                Toast.makeText(FuelDetailsVehicleOwnerView.this, "Wrong!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(FuelDetailsVehicleOwnerView.this, "Exception!", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.e("Error", error.toString());
                    }
                });
        queue.add(jsonObjectRequest);

    }


}