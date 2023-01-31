package org.token.lizan.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.token.lizan.models.HistoryModel;
import org.token.lizan.repository.DatabaseRepo;

import java.util.List;

public class SearchViewModel extends AndroidViewModel {

    private final DatabaseRepo repository;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        repository = DatabaseRepo.getInstance(application);
    }

    public LiveData<Boolean> insert(HistoryModel... historyModels) {
        return repository.insert(historyModels);
    }

    public LiveData<Boolean> update(HistoryModel historyModels) {
        return repository.update(historyModels);
    }

    public LiveData<Boolean> delete(HistoryModel... historyModels) {
        return repository.delete(historyModels);
    }

    public LiveData<Boolean> deleteAll(int type) {
        return repository.deleteAll(type);
    }

    public LiveData<List<HistoryModel>> getHistories() {
        return repository.getHistories();
    }

    public LiveData<List<HistoryModel>> getHistories(String text) {
        return repository.getHistories(text);
    }
}
