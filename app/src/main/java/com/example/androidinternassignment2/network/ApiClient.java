package com.example.androidinternassignment2.network;

import com.example.androidinternassignment2.utils.Credentials;
import com.example.androidinternassignment2.utils.DataInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiClient apiClient;
    private static Retrofit retrofit;

    private ApiClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Credentials.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ApiClient getApiClient(){
        if (apiClient == null){
            apiClient = new ApiClient();
        }
        return apiClient;
    }

    public DataInterface getDataInterface(){
        return retrofit.create(DataInterface.class);
    }
}
