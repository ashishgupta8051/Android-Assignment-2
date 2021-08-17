package com.example.androidinternassignment2;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidinternassignment2.adapter.CrewDetailsAdapter;
import com.example.androidinternassignment2.model.CrewDetails;
import com.example.androidinternassignment2.network.ConnectionLiveData;
import com.example.androidinternassignment2.network.ConnectionModel;
import com.example.androidinternassignment2.utils.ClickListener;
import com.example.androidinternassignment2.viewmodel.CrewViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ClickListener {
    public static final int WifiData = 1;
    public static final int MobileData = 2;
    private ConnectionLiveData connectionLiveData;
    private CrewViewModel crewViewModel;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CrewDetailsAdapter crewDetailsAdapter;
    private ProgressBar progressBar;
    private ImageView deleteAllDetailsImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUi();

        checkInternetConnection();

        deleteAllDetailsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete all details");
                builder.setMessage("Are you sure you want to delete all the details");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearAllData(dialog);
                    }
                });
                builder.setNegativeButton("No",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.setCancelable(false).create();
                alertDialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Toast;
                alertDialog.show();
            }
        });

      }

    @SuppressLint("NotifyDataSetChanged")
    private void clearAllData(DialogInterface dialog) {
        crewViewModel.getCrewDetails().observe(this,response ->{
            for (int i = 0;i < response.size();i++){
                CrewDetails crewDetails = response.get(i);
                crewViewModel.removeAllDetails(crewDetails);
            }
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(null);
            crewViewModel.getCrewDetail().observe(this,response2 ->{
                progressBar.setVisibility(View.GONE);
                crewDetailsAdapter.getCrewData(response2);
                recyclerView.setAdapter(crewDetailsAdapter);
            });
            dialog.dismiss();
        });
    }

    private void checkInternetConnection() {
        connectionLiveData.observe(this, new Observer<ConnectionModel>() {
            @Override
            public void onChanged(@Nullable ConnectionModel connection) {
                assert connection != null;
                if (connection.getIsConnected()) {
                    switch (connection.getType()) {
                        case WifiData:
                        case MobileData:
                            getCrewDetails();
                            break;
                    }
                } else {
                    getCrewDetail();
                    Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getCrewDetail() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(null);
        crewViewModel.getCrewDetail().observe(this,response ->{
            progressBar.setVisibility(View.GONE);
            crewDetailsAdapter.getCrewData(response);
            recyclerView.setAdapter(crewDetailsAdapter);
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getCrewDetails() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(null);
        crewViewModel.getCrewDetails().observe(this,response ->{
            for (int i = 0;i < response.size();i++){
                CrewDetails crewDetails = response.get(i);
                crewViewModel.addCrewDetails(crewDetails);
            }
            progressBar.setVisibility(View.GONE);
            crewDetailsAdapter.getCrewData(response);
            recyclerView.setAdapter(crewDetailsAdapter);
            crewDetailsAdapter.notifyDataSetChanged();
        });
    }

    private void setUpUi() {
        crewViewModel = new ViewModelProvider(this).get(CrewViewModel.class);
        connectionLiveData = new ConnectionLiveData(getApplicationContext());

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        crewDetailsAdapter = new CrewDetailsAdapter(this,this);
        recyclerView.setAdapter(crewDetailsAdapter);

        deleteAllDetailsImg = findViewById(R.id.deleteAllDetails);

    }

    @Override
    public void onClickListener(CrewDetails crewDetails) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(crewDetails.getWikipedia()));
        startActivity(intent);
    }
}