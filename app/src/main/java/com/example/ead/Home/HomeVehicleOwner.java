package com.example.ead.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ead.R;
import com.example.ead.View.VehicleOwnerStationsView;

public class HomeVehicleOwner extends AppCompatActivity {

    private Button vOStationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        vOStationBtn = findViewById(R.id.vOStationBtn);


        vOStationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeVehicleOwner.this, VehicleOwnerStationsView.class);
                startActivity(intent);
            }
        });
    }
}