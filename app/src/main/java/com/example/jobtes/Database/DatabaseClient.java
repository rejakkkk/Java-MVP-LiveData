package com.example.jobtes.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.jobtes.Model.Member;

@Database(entities = {Member.class}, version = 1, exportSchema = false)
public abstract class DatabaseClient extends RoomDatabase {

    private static DatabaseClient instance;

    public abstract DatabaseDao databaseDao();

    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseClient.class, "member_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}