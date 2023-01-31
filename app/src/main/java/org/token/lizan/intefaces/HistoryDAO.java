package org.token.lizan.intefaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.token.lizan.models.HistoryModel;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


@Dao
public interface HistoryDAO {
    @Insert
    void insert(HistoryModel... historyModels);

    @Update
    void update(HistoryModel historyModel);

    @Delete
    void delete(HistoryModel... historyModel);

    @Query("SELECT * FROM history ORDER BY date DESC")
    Flowable<List<HistoryModel>> getHistories();

    @Query("SELECT * FROM history where text ==:name")
    Flowable<List<HistoryModel>> getHistories(String name);

    @Query("DELETE FROM history WHERE type==:type")
    void deleteAll(int type);
}
