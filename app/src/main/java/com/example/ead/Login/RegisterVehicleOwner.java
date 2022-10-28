package com.example.ead.Login;

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
import com.example.ead.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterVehicleOwner extends AppCompatActivity {

    private TextView name,nic,VehicleNo,chNo,fuelType,vehicleType,password;
    private String userType = "VEHICLE_OWNER";
    private Button btn;
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_vehicle_owner);

        name = (TextView) findViewById(R.id.regVOName);
        nic = (TextView) findViewById(R.id.regVONicNo);
        chNo = (TextView) findViewById(R.id.regChNo);
        VehicleNo = (TextView) findViewById(R.id.regVhNo);
        fuelType = (TextView) findViewById(R.id.regVoFtype);
        vehicleType=(TextView) findViewById(R.id.regVoVehiType);
        password = (TextView) findViewById(R.id.regVoPass);
        btn = findViewById(R.id.regVOBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterVOwner();
            }
        });
    }

    //Register vehicle owner
    private void RegisterVOwner(){

        HashMap<String,String> params = new HashMap<String,String>();

        params.put("name",name.getText().toString());
        params.put("NIC",nic.getText().toString());
        params.put("chasisNumber",chNo.getText().toString());
        params.put("vehicleNumber",VehicleNo.getText().toString());
        params.put("fuelType",fuelType.getText().toString());
        params.put("vehicleType",vehicleType.getText().toString());
        params.put("userType",userType);
        params.put("password",password.getText().toString());



        queue = Volley.newRequestQueue(this);
        String url = "https://pasindu-fuelapi.herokuapp.com/users/register";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Success ",response.toString());

                        try {
                            if(response.get("isSuccessful").equals(true)){
                                Toast.makeText(RegisterVehicleOwner.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterVehicleOwner.this, LoginVehicleOwner.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(RegisterVehicleOwner.this, "Some ting went wrong!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterVehicleOwner.this, "Some ting went wrong!!", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(RegisterVehicleOwner.this, "Some ting went wrong!!", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(jsonObjectRequest);

    }
}