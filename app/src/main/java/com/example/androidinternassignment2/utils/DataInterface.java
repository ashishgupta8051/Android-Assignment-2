package com.example.androidinternassignment2.utils;

import com.example.androidinternassignment2.model.CrewDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataInterface {

    @GET("v4/crew")
    Call<List<CrewDetails>> getCrewDetails();
}
