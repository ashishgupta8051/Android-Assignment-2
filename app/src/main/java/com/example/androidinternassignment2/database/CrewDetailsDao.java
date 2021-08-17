package com.example.androidinternassignment2.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.androidinternassignment2.model.CrewDetails;

import java.util.List;

@Dao
public interface CrewDetailsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addCrewDetails(CrewDetails crewDetails);

    @Query("select * from crewTable")
    LiveData<List<CrewDetails>> getCrewDetails();

    @Delete
    void deleteCrewDetails(CrewDetails crewDetails);

}
