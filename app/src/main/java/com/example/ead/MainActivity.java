package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ead.Login.LoginStationOwner;
import com.example.ead.Login.LoginVehicleOwner;


public class MainActivity extends AppCompatActivity {
    private Button voBtn;
    private Button sOBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        voBtn =(Button) findViewById(R.id.vehicleOMBtn);
        sOBtn =(Button) findViewById(R.id.stationOMBtn);

        voBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginVehicleOwner.class);
                startActivity(intent);
            }
        });

        sOBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginStationOwner.class);
                startActivity(intent);
            }
        });

    }
}