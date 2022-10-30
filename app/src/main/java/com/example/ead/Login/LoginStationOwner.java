package com.example.ead.Login;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;




import com.example.ead.Home.HomeStationOwner;
import com.example.ead.R;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

//create login for signIn for StationOwner
public class LoginStationOwner extends AppCompatActivity {

    private TextView signUp,name,password;
    private String userType = "STATION_OWNER";
    private Button btn;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_station_owner);

        name = (TextView) findViewById(R.id.loginInSRNo);
        password = (TextView) findViewById(R.id.loginInSPass);
        signUp = (TextView) findViewById(R.id.noAcStationRegText);
        btn = findViewById(R.id.loginInSOBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginAsStationOwner();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginStationOwner.this, RegisterStationOwner.class);
                startActivity(intent);
            }
        });
    }

    //create post method
    private void LoginAsStationOwner(){

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
                                Toast.makeText(LoginStationOwner.this, "Successfully logged in", Toast.LENGTH_SHORT).show();

                                JSONObject user = response.getJSONObject("user");
                                String id = user.getString("id");
                                Intent intent = new Intent(LoginStationOwner.this, HomeStationOwner.class);
                                intent.putExtra("sid", id);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(LoginStationOwner.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(LoginStationOwner.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(LoginStationOwner.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);

    }
}