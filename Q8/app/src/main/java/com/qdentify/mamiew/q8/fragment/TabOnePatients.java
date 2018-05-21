package com.qdentify.mamiew.q8.fragment;
/*
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.qdentify.mamiew.q8.R;
import com.qdentify.mamiew.q8.activity.PersonInfoActivity;
import com.qdentify.mamiew.q8.adapter.PatientListAdapter;
import com.qdentify.mamiew.q8.dao.PatientListColletionDao;
import com.qdentify.mamiew.q8.dao.PatientListDao;
import com.qdentify.mamiew.q8.manager.Contextor;
import com.qdentify.mamiew.q8.manager.http.ApiService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TabOnePatients extends Fragment implements PatientListAdapter.OnItemClickListener{
    /*CardView patientCard;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_one_patients,container,false);
        patientCard = (CardView)rootView.findViewById(R.id.patient_card);
        patientCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "You click on card", Toast.LENGTH_LONG).show();
                Intent info = new Intent(getActivity(),PersonInfoActivity.class);
                startActivity(info);
            }
        });
        return rootView;
    }*/

/*
    private RecyclerView pRecyclerView;
    private PatientListAdapter pAadapter;
    //private RecyclerView.LayoutManager pLayoutManager;
    private ApiService service;
    private ProgressBar progressBar;
    public PatientListColletionDao patientListColletionDao;
    public PatientListDao patientListDaoParcelable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_one_patients,container,false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        progressBar = (ProgressBar)rootView.findViewById(R.id.progress_bar);
        progressBar.setVisibility(ProgressBar.VISIBLE);
        pRecyclerView = (RecyclerView) rootView.findViewById(R.id.patient_view);
        pRecyclerView.setHasFixedSize(true);
        pRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);

        Call<PatientListColletionDao> call = service.loadPatientList();
        call.enqueue(new Callback<PatientListColletionDao>() {

            @Override
            public void onResponse(Call<PatientListColletionDao> call, Response<PatientListColletionDao> response) {
                if(response.isSuccessful()){
                    PatientListColletionDao dao = response.body();
                    //PatientListManager.getInstance().setDao(dao);
                    pAadapter = new PatientListAdapter(dao.getData());
                    pRecyclerView.setAdapter(pAadapter);
                    //Toast.makeText(Contextor.getInstance().getContext(),dao.getData().get(0).getName(),Toast.LENGTH_SHORT).show();

                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                    patientListColletionDao = new PatientListColletionDao();
                    patientListColletionDao.setData(dao.getData());

                    pAadapter.setOnItemClickListener(TabOnePatients.this);

                } else {
                    try {
                        Log.d("call","else naja");
                        Toast.makeText(Contextor.getInstance().getContext(),response.errorBody().string(),Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<PatientListColletionDao> call, Throwable t) {
                Toast.makeText(Contextor.getInstance().getContext(),t.toString(),Toast.LENGTH_SHORT).show();
            }
        });




        /*Call<PatientListDao> call = HttpManager.getInstance().getService().loadPatientList();
        call.enqueue(new Callback<PatientListDao>() {

            @Override
            public void onResponse(Call<PatientListDao> call, Response<PatientListDao> response) {
                if(response.isSuccessful()){
                    PatientListDao dao = response.body();
                    PatientListManager.getInstance().setDao(dao);
                    Toast.makeText(Contextor.getInstance().getContext(),dao.getName(),Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Toast.makeText(Contextor.getInstance().getContext(),response.errorBody().string(),Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<PatientListDao> call, Throwable t) {
                //Handle
                Toast.makeText(Contextor.getInstance().getContext(),t.toString(),Toast.LENGTH_SHORT).show();

            }

        });*/

        /*String[] mPatient = {"Manee", "Meena", "Chalee"};


        pRecyclerView.setHasFixedSize(true);
        //pLayoutManager = new LinearLayoutManager(this.getActivity());
        pRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        pAadapter = new PatientListAdapter(mPatient);
        pRecyclerView.setAdapter(pAadapter);*/
/*
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getActivity(), "You click on card", Toast.LENGTH_LONG).show();
        Intent info = new Intent(getActivity(),PersonInfoActivity.class);
        Log.d("TabOne","pParcelable going");
        patientListDaoParcelable = new PatientListDao();
        patientListDaoParcelable.setpID(patientListColletionDao.getData().get(position).getpID());
        patientListDaoParcelable.setFirstName(patientListColletionDao.getData().get(position).getFirstName());
        patientListDaoParcelable.setLastName(patientListColletionDao.getData().get(position).getLastName());
        patientListDaoParcelable.setDob(patientListColletionDao.getData().get(position).getDob());
        patientListDaoParcelable.setBloodType(patientListColletionDao.getData().get(position).getBloodType());
        patientListDaoParcelable.setDisease(patientListColletionDao.getData().get(position).getDisease());
        patientListDaoParcelable.setRegDosing(patientListColletionDao.getData().get(position).getRegDosing());
        patientListDaoParcelable.setDrugAllergies(patientListColletionDao.getData().get(position).getDrugAllergies());
        patientListDaoParcelable.setHospital(patientListColletionDao.getData().get(position).getHospital());
        patientListDaoParcelable.setContact(patientListColletionDao.getData().get(position).getContact());
        info.putExtra("pParcelable", patientListDaoParcelable);
        Log.d("TabOne","pParcelable success");
        startActivity(info);
    }
}*/