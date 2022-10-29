package com.example.ead.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ead.R;

import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.ViewHolder> {

    LayoutInflater inflater;
    Context context;
    List<Station> stationList;
//    ProgressBar progressBar;


    public StationAdapter(Context ctx, List<Station> stationList){
        this.context =  ctx;
        this.inflater = LayoutInflater.from(ctx);
        this.stationList = stationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.stationlist,parent,false);
//        view.setOnClickListener(mOnClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //bind data
        holder.station.setText(stationList.get(position).getName());

        holder.station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.e("test",stationList.get(position).getStationNo());

                final Intent intent = new Intent(context, FuelDetailsVehicleOwnerView.class);

                //pass data to FuelDetailsStationOwnerView screen
                intent.putExtra("Station_id", stationList.get(position).getStationNo());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView station;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            progressBar = itemView.findViewById(R.id.progressBarId);
            station = itemView.findViewById(R.id.stationName);

//            onDataChange();
        }
//        public void onClick(final View view) {
//            int itemPosition = mRecyclerView.getChildLayoutPosition(view);
//            String item = mList.get(itemPosition);
//            Toast.makeText(mContext, item, Toast.LENGTH_LONG).show();
//        }


    }


//    public void onDataChange(){
//        if(stationList != null){
//            progressBar.setVisibility(View.GONE);
//        }
//    }
}
