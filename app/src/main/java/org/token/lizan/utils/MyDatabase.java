package org.token.lizan.utils;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.token.lizan.intefaces.HistoryDAO;
import org.token.lizan.models.HistoryModel;


@Database(entities = {HistoryModel.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    private static MyDatabase historyDatabase;

    public static MyDatabase getInstance(Application application) {
        if (historyDatabase == null) {
            historyDatabase = Room.databaseBuilder(application.getApplicationContext(), MyDatabase.class, "data.db")
                    .fallbackToDestructiveMigration().build();
        }
        return historyDatabase;
    }

    public abstract HistoryDAO getHistoryDao();

}
