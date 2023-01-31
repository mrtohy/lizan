package org.token.lizan.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.token.lizan.BuildConfig;
import org.token.lizan.utils.Contacts;

public class SplashScreenViwModel extends AndroidViewModel {
    private final RequestQueue requestQueue;

    public SplashScreenViwModel(@NonNull Application application) {
        super(application);
        requestQueue = Volley.newRequestQueue(application);
    }

    private MutableLiveData<Boolean> retry;

    public LiveData<Boolean> retry() {
        if (retry == null) {
            retry = new MutableLiveData<>();
        }
        return retry;
    }

    private MutableLiveData<Throwable> request;

    public LiveData<Throwable> responseRequest() {
        if (request == null) {
            request = new MutableLiveData<>();
            sendRequest();
        }
        return request;
    }

    public void sendRequest() {
        StringRequest stringRequest = new StringRequest(Contacts.URL, response -> {
            retry.setValue(false);
            request.setValue(null);
        }, error -> {
            retry.setValue(true);
            request.setValue(error);
        });
        stringRequest.addMarker(getClass().getSimpleName());
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (requestQueue != null) {
            requestQueue.cancelAll(getClass().getSimpleName());
        }
    }
}
