package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.ead.View.FuelDetailsVehicleOwnerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class InputFuelStatus extends AppCompatActivity {

    private TextView enterLiters;
    private Button exiBtn,LeaveBtn;
    private RequestQueue queue;
    private String fType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_fuel_status);

        enterLiters = (TextView) findViewById(R.id.enterLiters);
        exiBtn = findViewById(R.id.exiBtn);
        LeaveBtn = findViewById(R.id.LeaveBtn);

//        Log.e("exit", String.valueOf(enterLiters.getText().toString().isEmpty());
        if(enterLiters.getText().toString().isEmpty()){
            exiBtn.setEnabled(false);
            LeaveBtn.setEnabled(true);
            exiBtn.setBackgroundResource(R.drawable.round_yellow_box);
            LeaveBtn.setBackgroundResource(R.drawable.round_red_box);
        }
        else{
            exiBtn.setEnabled(true);
            LeaveBtn.setEnabled(false);
            LeaveBtn.setBackgroundResource(R.drawable.round_dred_box);
            exiBtn.setBackgroundResource(R.drawable.round_green_button);
        }



        enterLiters.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(enterLiters.getText().toString().isEmpty()){
                    exiBtn.setEnabled(false);
                    LeaveBtn.setEnabled(true);
                    exiBtn.setBackgroundResource(R.drawable.round_green_button);
                    LeaveBtn.setBackgroundResource(R.drawable.round_red_box);
                }
                else{
                    exiBtn.setEnabled(true);
                    LeaveBtn.setEnabled(false);
                    LeaveBtn.setBackgroundResource(R.drawable.round_dred_box);
                    exiBtn.setBackgroundResource(R.drawable.round_yellow_box);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        exiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("exit","exit");
                LoginStationOwner();
//                Intent intent = new Intent(FuelDetailsVehicleOwnerView.this, HomeVehicleOwner.class);
//                startActivity(intent);
            }
        });
        LeaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("exit","Leave");
//                Intent intent = new Intent(FuelDetailsVehicleOwnerView.this, HomeVehicleOwner.class);
//                startActivity(intent);
            }
        });


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_d:
                if (checked)
                    fType = "Diesel";
                break;
            case R.id.radio_sd:
                if (checked)
                    fType = "Super diesel";
                break;
            case R.id.radio_p92:
                if (checked)
                    fType = "Petrol 92";
                break;
            case R.id.radio_p95:
                if (checked)
                    fType = "Petrol 95";
                break;
        }
    }

    private void LoginStationOwner(){

        HashMap<String,String> params = new HashMap<String,String>();

        params.put("fuelType",fType);
        params.put("quantity",enterLiters.getText().toString());

        Log.e("Success",params.toString());

        String id = "635ab20ea8993ddd3ac9d9dc";
        queue = Volley.newRequestQueue(this);
        String url = "https://pasindu-fuelapi.herokuapp.com/fuelStations/reduceFuel/"+id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PUT, url, new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Success ",response.toString());

                        try {
                            if(response.get("isSuccessful").equals(true)){
                                Toast.makeText(InputFuelStatus.this, "Successfully Exit", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(InputFuelStatus.this, FuelDetailsVehicleOwnerView.class);
                                startActivity(intent);

                            }
                            else{
                                Toast.makeText(InputFuelStatus.this, "Some ting went wrong!!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(InputFuelStatus.this, "Some ting went wrong!!", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);

    }



}