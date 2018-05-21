package com.qdentify.mamiew.q6;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by miew_ on 3/20/2018.
 */
/*
public class PatientsListAdapter extends BaseAdapter {
    private Context mContext;
    private List<Patients> mPatientList;

    //constructor

    public PatientsListAdapter(Context mContext, List<Patients> mPatientList) {
        this.mContext = mContext;
        this.mPatientList = mPatientList;
    }

    @Override
    public int getCount() {
        return mPatientList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPatientList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.content_view_patients,null);
        TextView tv_name = (TextView)v.findViewById(R.id.tv_pname);
        TextView tv_pdescript = (TextView)v.findViewById(R.id.tv_pdescript);
        //Set text for textview
        tv_name.setText(String.valueOf(mPatientList.get(position).getPname()) + " $");
        tv_pdescript.setText(String.valueOf(mPatientList.get(position).getPdescription()));

        v.setTag(mPatientList.get(position).getId());


        return v;
    }
}
*/