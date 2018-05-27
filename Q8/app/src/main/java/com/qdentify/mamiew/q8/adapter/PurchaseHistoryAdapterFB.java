package com.qdentify.mamiew.q8.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qdentify.mamiew.q8.R;
import com.qdentify.mamiew.q8.dao.PatientListModelFB;
import com.qdentify.mamiew.q8.dao.PurchaseObject;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PurchaseHistoryAdapterFB extends RecyclerView.Adapter<PurchaseHistoryAdapterFB.ViewHolder> {
    private List<PurchaseObject> purchaseObjectList;
    private OnItemClickListener pListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        pListener = listener;
    }

    public PurchaseHistoryAdapterFB(List<PurchaseObject> result) {
        this.purchaseObjectList = result;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_patient,parent,false));
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_purchse_history,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView, pListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.bindData(patientListModelFBList.get(position));
        PurchaseObject purchaseObject = purchaseObjectList.get(position);
        holder.tvName.setText(purchaseObject.getName());
        holder.tvTagType.setText(purchaseObject.getTagType());
        holder.tvTotal.setText(purchaseObject.getTotal());

        Picasso.with(holder.itemView.getContext()).load("")
                .placeholder(R.drawable.progress_animation)
                .into(holder.imThumbnail);
        /*Glide.with(holder.itemView.getContext()).load(patientListModelFB.thumbnail)
                .into(holder.imThumbnail);*/
    }

    @Override
    public int getItemCount() {
        return purchaseObjectList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvTagType, tvTotal;
        public ImageView imThumbnail;

        public ViewHolder(View itemLayoutView, final OnItemClickListener listener) {
            super(itemLayoutView);
            tvName = (TextView) itemLayoutView.findViewById(R.id.tv_name);
            tvTagType = (TextView) itemLayoutView.findViewById(R.id.tv_tag_type);
            tvTotal = (TextView) itemLayoutView.findViewById(R.id.tv_total);

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
