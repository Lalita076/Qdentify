package com.qdentify.mamiew.q8.adapter;
/*
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qdentify.mamiew.q8.R;
import com.qdentify.mamiew.q8.dao.PatientListColletionDao;
import com.qdentify.mamiew.q8.dao.PatientListDao;

import java.util.List;

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.ViewHolder> {

    private List<PatientListDao> patientListDaoList;
    private OnItemClickListener pListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        pListener = listener;
    }

    public PatientListAdapter(List<PatientListDao> patientListDaoList) {
        this.patientListDaoList = patientListDaoList;
    }

    @Override
    public PatientListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_patient,null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView, pListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(patientListDaoList.get(position).getFirstName() +" "+ patientListDaoList.get(position).getLastName());
        holder.tvBlood.setText(patientListDaoList.get(position).getBloodType());
        holder.tvDob.setText(patientListDaoList.get(position).getDob());

    }

    @Override
    public int getItemCount() {
        return patientListDaoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvBlood, tvDob, tvDiseases;

        public ViewHolder(View itemLayoutView, final OnItemClickListener listener) {
            super(itemLayoutView);
            tvName = (TextView)itemLayoutView.findViewById(R.id.tv_name);
            tvBlood = (TextView)itemLayoutView.findViewById(R.id.tv_blood);
            tvDob = (TextView)itemLayoutView.findViewById(R.id.tv_dob);
            tvDiseases = (TextView)itemLayoutView.findViewById(R.id.tv_diseases);

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
*/