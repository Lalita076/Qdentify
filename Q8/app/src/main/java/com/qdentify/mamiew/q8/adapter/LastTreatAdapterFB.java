package com.qdentify.mamiew.q8.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qdentify.mamiew.q8.R;
import com.qdentify.mamiew.q8.activity.LastTreatActivity;
import com.qdentify.mamiew.q8.dao.LastTreatModel;
import com.qdentify.mamiew.q8.dao.PatientListModelFB;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LastTreatAdapterFB extends RecyclerView.Adapter<LastTreatAdapterFB.ViewHolder> {
    private List<LastTreatModel> lastTreatList;
    private OnItemClickListener ltListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        ltListener = listener;
    }

    public LastTreatAdapterFB(List<LastTreatModel> lastTreatList) {
        this.lastTreatList = lastTreatList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_patient,parent,false));
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_last_treat,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView, ltListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LastTreatModel lastTreat = lastTreatList.get(position);
        holder.tvDate.setText(lastTreat.date);
        holder.tvHospital.setText(lastTreat.hospital);
        holder.tvDoctor.setText(lastTreat.doctorName);
        holder.tvSymptom.setText(lastTreat.symptom);

    }

    @Override
    public int getItemCount() {
        return lastTreatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDate, tvHospital, tvDoctor, tvSymptom;
        public ImageView imThumbnail;

        public ViewHolder(View itemLayoutView, final OnItemClickListener listener) {
            super(itemLayoutView);
            tvDate = (TextView) itemLayoutView.findViewById(R.id.tv_lt_date);
            tvHospital = (TextView) itemLayoutView.findViewById(R.id.tv_lt_hospital);
            tvDoctor = (TextView) itemLayoutView.findViewById(R.id.tv_lt_dc_name);
            tvSymptom = (TextView) itemLayoutView.findViewById(R.id.tv_lt_symptom);

            itemLayoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }

    }
}
