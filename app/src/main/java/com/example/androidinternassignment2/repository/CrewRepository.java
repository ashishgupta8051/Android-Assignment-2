package com.example.androidinternassignment2.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidinternassignment2.client.ApiClient;
import com.example.androidinternassignment2.database.CrewDetailsDao;
import com.example.androidinternassignment2.model.CrewDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrewRepository {
    private CrewDetailsDao crewDetailsDao;
    MutableLiveData<List<CrewDetails>> liveData = new MutableLiveData<>();

    public CrewRepository(CrewDetailsDao crewDetailsDao) {
        this.crewDetailsDao = crewDetailsDao;
    }

    public LiveData<List<CrewDetails>> getCrewDetails(){
        Call<List<CrewDetails>> call = ApiClient.getApiClient().getDataInterface().getCrewDetails();
        call.enqueue(new Callback<List<CrewDetails>>() {
            @Override
            public void onResponse(@NonNull Call<List<CrewDetails>> call, @NonNull Response<List<CrewDetails>> response) {
                liveData.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<CrewDetails>> call, @NonNull Throwable t) {
                Log.e(TAG,t.getMessage());
            }
        });

        return liveData;
    }

    public void addCrewDetails(CrewDetails crewDetails){
        crewDetailsDao.addCrewDetails(crewDetails);
    }

    public LiveData<List<CrewDetails>> getCrewDetail(){
        return crewDetailsDao.getCrewDetails();
    }

    public void removeAllDetails(CrewDetails crewDetails){
        crewDetailsDao.deleteCrewDetails(crewDetails);
    }

}
