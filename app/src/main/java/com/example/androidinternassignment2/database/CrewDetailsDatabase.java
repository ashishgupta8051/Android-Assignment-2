package com.example.androidinternassignment2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.androidinternassignment2.model.CrewDetails;
import com.example.androidinternassignment2.utils.Credentials;

@Database(entities = CrewDetails.class, version = 1, exportSchema = false)
public abstract class CrewDetailsDatabase extends RoomDatabase {

    public abstract CrewDetailsDao crewDetailsDao();

    private static CrewDetailsDatabase instance;

    public static CrewDetailsDatabase getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context, CrewDetailsDatabase.class, Credentials.DATABASE)
                    .allowMainThreadQueries().build();
        return instance;
    }
}
