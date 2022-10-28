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
import com.example.ead.Home.HomeVehicleOwner;
import com.example.ead.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

//create login for signIn for Vehicle owner
public class LoginVehicleOwner extends AppCompatActivity {

    private TextView signUp,name,password;
    private String userType = "VEHICLE_OWNER";
    private Button btn;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_vehicle_owner);

        name = (TextView) findViewById(R.id.loginInDVNo);
        password = (TextView) findViewById(R.id.loginDPass);
        signUp = (TextView) findViewById(R.id.noAcReg);
        btn = findViewById(R.id.loginVOBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginAsDriver();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginVehicleOwner.this, RegisterVehicleOwner.class);
                startActivity(intent);
            }
        });
    }

    // post request for login
    private void LoginAsDriver(){

        HashMap<String,String> params = new HashMap<String,String>();

        params.put("uniqueIdentifier",name.getText().toString());
        params.put("password",password.getText().toString());
        params.put("userType",userType);

        queue = Volley.newRequestQueue(this);
        String url = "https://pasindu-fuelapi.herokuapp.com/users/login";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Success ",response.toString());

                        try {
                            if(response.get("isSuccessful").equals(true)){
                                Toast.makeText(LoginVehicleOwner.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginVehicleOwner.this, HomeVehicleOwner.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(LoginVehicleOwner.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(LoginVehicleOwner.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(LoginVehicleOwner.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);

    }
}