package com.example.androidinternassignment2.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidinternassignment2.database.CrewDetailsDao;
import com.example.androidinternassignment2.database.CrewDetailsDatabase;
import com.example.androidinternassignment2.model.CrewDetails;
import com.example.androidinternassignment2.repository.CrewRepository;

import java.util.List;

public class CrewViewModel extends AndroidViewModel {

    CrewRepository crewRepository;

    public CrewViewModel(Application application){
        super(application);
        CrewDetailsDao crewDetailsDao = CrewDetailsDatabase.getInstance(application).crewDetailsDao();
        crewRepository = new CrewRepository(crewDetailsDao);
    }

    public LiveData<List<CrewDetails>> getCrewDetails(){
        return crewRepository.getCrewDetails();
    }

    public void addCrewDetails(CrewDetails crewDetails){
        crewRepository.addCrewDetails(crewDetails);
    }

    public LiveData<List<CrewDetails>> getCrewDetail(){
        return crewRepository.getCrewDetail();
    }

    public void removeAllDetails(CrewDetails crewDetails){
        crewRepository.removeAllDetails(crewDetails);
    }

}
