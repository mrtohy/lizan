package org.token.lizan.repository;

import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;

import org.token.lizan.models.HistoryModel;
import org.token.lizan.utils.MyDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DatabaseRepo {
    public static CompositeDisposable disposable;
    private static Application app;
    private static MyDatabase database;
    private static DatabaseRepo repo;

    public static DatabaseRepo getInstance(Application application) {
        if (repo == null) {
            repo = new DatabaseRepo();
        }
        app = application;
        disposable = new CompositeDisposable();
        database = MyDatabase.getInstance(application);
        return repo;
    }

    public LiveData<Boolean> insert(HistoryModel... historyModels) {

        MutableLiveData<Boolean> insert = new MutableLiveData<>();
        Completable.fromCallable((Callable<Boolean>) () -> {
            database.getHistoryDao().insert(historyModels);
            return true;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onComplete() {
                insert.setValue(true);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
        return insert;
    }

    public LiveData<Boolean> update(HistoryModel historyModels) {
        MutableLiveData<Boolean> update = new MutableLiveData<>();
        Completable.fromCallable((Callable<Boolean>) () -> {
            database.getHistoryDao().update(historyModels);
            return true;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onComplete() {
                update.setValue(true);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
        return update;
    }

    public LiveData<Boolean> delete(HistoryModel... historyModels) {
        MutableLiveData<Boolean> delete = new MutableLiveData<>();
        Completable.fromCallable((Callable<Boolean>) () -> {
            database.getHistoryDao().delete(historyModels);
            return true;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onComplete() {
                delete.setValue(true);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
        return delete;
    }

    public LiveData<List<HistoryModel>> getHistories() {
        return LiveDataReactiveStreams.fromPublisher(database.getHistoryDao().getHistories().subscribeOn(Schedulers.io()).onErrorReturn(throwable -> new ArrayList<>()));
    }

    public LiveData<List<HistoryModel>> getHistories(String name) {
        return LiveDataReactiveStreams.fromPublisher(database.getHistoryDao().getHistories(name).subscribeOn(Schedulers.io()).onErrorReturn(throwable -> new ArrayList<>()));
    }

    public LiveData<Boolean> deleteAll(int type) {
        MutableLiveData<Boolean> deleteAll = new MutableLiveData<>();

        Completable.fromCallable((Callable<Boolean>) () -> {
            database.getHistoryDao().deleteAll(type);
            return true;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onComplete() {
                deleteAll.setValue(true);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
        return deleteAll;
    }

    public LiveData<Boolean> copy(String text) {
        MutableLiveData<Boolean> copy = new MutableLiveData<>();
//        TrackHelper.trackEvent(app.getApplicationContext(), text, 1L, "view", "analytic");
        Disposable d = Single.fromCallable(() -> {
            HistoryModel historyModel = new HistoryModel(0, text, 2, new Date().toString());
            insert(historyModel);
            ClipboardManager clipboardManager = (ClipboardManager) app.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("copy", text);
            clipboardManager.setPrimaryClip(clipData);
            return clipboardManager.hasPrimaryClip();
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((aBoolean, throwable) -> copy.setValue(aBoolean));
        disposable.add(d);
        return copy;
    }

}
