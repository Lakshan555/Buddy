package com.example.ead.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ead.R;
import com.example.ead.View.FuelDetailsStationOwnerView;

public class HomeStationOwner extends AppCompatActivity {
    Button stationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_station_owner);

        stationBtn = findViewById(R.id.stationBtn);


        stationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = getIntent();

                String sid = intent1.getStringExtra("sid");

                Intent intent = new Intent(HomeStationOwner.this, FuelDetailsStationOwnerView.class);
                intent.putExtra("sid", sid);
                Log.e("testis",sid);
                startActivity(intent);
            }
        });
    }



}