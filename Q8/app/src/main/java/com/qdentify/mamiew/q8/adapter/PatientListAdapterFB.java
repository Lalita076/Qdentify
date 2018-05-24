package com.qdentify.mamiew.q8.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qdentify.mamiew.q8.R;
import com.qdentify.mamiew.q8.dao.PatientListDao;
import com.qdentify.mamiew.q8.dao.PatientListModelFB;
import com.qdentify.mamiew.q8.fragment.TabOnePatientsFB;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

public class PatientListAdapterFB extends RecyclerView.Adapter<PatientListAdapterFB.ViewHolder> {
    private List<PatientListModelFB> patientListModelFBList;
    private OnItemClickListener pListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        pListener = listener;
    }

    public PatientListAdapterFB(List<PatientListModelFB> result) {
        this.patientListModelFBList = result;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_patient,parent,false));
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_patient,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView, pListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.bindData(patientListModelFBList.get(position));
        PatientListModelFB patientListModelFB = patientListModelFBList.get(position);
        holder.tvName.setText(patientListModelFB.firstName+" "+patientListModelFB.lastName);
        holder.tvBlood.setText(patientListModelFB.bloodType);
        holder.tvDob.setText(patientListModelFB.dob);
        holder.tvDiseases.setText(patientListModelFB.disease);
        Picasso.with(holder.itemView.getContext()).load(patientListModelFB.thumbnail)
                .placeholder(R.drawable.progress_animation)
                .into(holder.imThumbnail);
        /*Glide.with(holder.itemView.getContext()).load(patientListModelFB.thumbnail)
                .into(holder.imThumbnail);*/
    }

    @Override
    public int getItemCount() {
        return patientListModelFBList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvBlood, tvDob, tvDiseases;
        public ImageView imThumbnail;

        public ViewHolder(View itemLayoutView, final OnItemClickListener listener) {
            super(itemLayoutView);
            tvName = (TextView) itemLayoutView.findViewById(R.id.tv_name);
            tvBlood = (TextView) itemLayoutView.findViewById(R.id.tv_blood);
            tvDob = (TextView) itemLayoutView.findViewById(R.id.tv_dob);
            tvDiseases = (TextView) itemLayoutView.findViewById(R.id.tv_diseases);
            imThumbnail = (ImageView) itemLayoutView.findViewById(R.id.im_thumbnail);

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
