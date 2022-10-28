package com.example.ead.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ead.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterStationOwner extends AppCompatActivity {

    private TextView name,nic,station_name,register_no,fuelType,password;
    private String userType = "STATION_OWNER";
    private Button btn;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiter_station_owner);

        name = (TextView) findViewById(R.id.regSOName);
        nic = (TextView) findViewById(R.id.regSONicNo);
        station_name = (TextView) findViewById(R.id.regSTNo);
        register_no = (TextView) findViewById(R.id.regSoReg);
        fuelType = (TextView) findViewById(R.id.regSoFtype);
        password = (TextView) findViewById(R.id.regSoPass);
        btn = findViewById(R.id.SignUpSOBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RegStationOwner();

            }
        });

    }
    // crate  Station owner
    private void RegStationOwner(){

        HashMap<String,String> params = new HashMap<String,String>();


        params.put("userName",name.getText().toString());
        params.put("NIC",nic.getText().toString());
        params.put("stationName",station_name.getText().toString());
        params.put("registerNo",register_no.getText().toString());
        params.put("fuelType",fuelType.getText().toString());
        params.put("userType",userType);
        params.put("password",password.getText().toString());

        queue = Volley.newRequestQueue(this);
        String url = "https://pasindu-fuelapi.herokuapp.com/users/register";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if(response.get("isSuccessful").equals(true)){

                                JSONObject user = response.getJSONObject("user");
                                String id = user.getString("id");

                                AddFuelStations(id);

                            }
                            else{
                                Toast.makeText(RegisterStationOwner.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterStationOwner.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(RegisterStationOwner.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);




    }

   // new fuel Station
    private void AddFuelStations(String id){

        HashMap<String,String> params = new HashMap<String,String>();

        params.put("ownerId",id);
        params.put("fuelStationName",station_name.getText().toString());
        params.put("registerNo",register_no.getText().toString());

        queue = Volley.newRequestQueue(this);
        String url = "https://pasindu-fuelapi.herokuapp.com/fuelStations/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.get("isSuccessful").equals(true)){
                                Log.e("Success ",response.toString());

                                JSONObject station = response.getJSONObject("fuelStation");
                                String sId = station.getString("_id");

                                Log.e("Success ",sId);

                                AddQueue(sId);

                            }
                            else{
                                Toast.makeText(RegisterStationOwner.this, "Wrong!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterStationOwner.this, "Exception!", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.e("Error",error.toString());
                    }
                });
        queue.add(jsonObjectRequest);

    }


    //add new queue
    private void AddQueue(String sId){
        HashMap<String,String> params = new HashMap<String,String>();

        queue = Volley.newRequestQueue(this);
        String url = "https://pasindu-fuelapi.herokuapp.com/queues/"+sId;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.get("isSuccessful").equals(true)){

                                Intent intent = new Intent(RegisterStationOwner.this, LoginStationOwner.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(RegisterStationOwner.this, "Wrong!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterStationOwner.this, "Exception!", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.e("Error",error.toString());
                    }
                });
        queue.add(jsonObjectRequest);

    }

}