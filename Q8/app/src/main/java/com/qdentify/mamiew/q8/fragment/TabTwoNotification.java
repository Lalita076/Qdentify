package com.qdentify.mamiew.q8.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.qdentify.mamiew.q8.R;

public class TabTwoNotification extends Fragment {


    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_two_notification,container,false);
        //initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        //progressBar = (ProgressBar)rootView.findViewById(R.id.progress_bar);
        //progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //progressBar.setVisibility(ProgressBar.INVISIBLE);
    }
}
