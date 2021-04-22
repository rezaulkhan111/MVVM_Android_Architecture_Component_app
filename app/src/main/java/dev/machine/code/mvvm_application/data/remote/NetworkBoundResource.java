package dev.machine.code.mvvm_application.data.remote;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.net.SocketTimeoutException;

import dev.machine.code.mvvm_application.R;
import dev.machine.code.mvvm_application.ThisApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
public abstract class NetworkBoundResource<T, V> {

    private final MediatorLiveData<Resource<T>> result = new MediatorLiveData<>();

    @MainThread
    protected NetworkBoundResource() {
        result.setValue(Resource.loading(null));
        // Always load the data from DB intially so that we have
        LiveData<T> dbSource = loadDataFromDb();
        // Fetch the data from network and add it to the resource
        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);
            if (shouldFetch()) {
                fetchFromNetwork(dbSource);
            } else {
                result.addSource(dbSource, newData -> {
                    if (null != newData)
                        result.setValue(Resource.success(newData));
                });
            }
        });
    }

    private void fetchFromNetwork(final LiveData<T> dbSource) {
        result.addSource(dbSource, newData -> result.setValue(Resource.loading(newData)));
        createCall().enqueue(new Callback<V>() {
            @Override
            public void onResponse(@NonNull Call<V> call, @NonNull Response<V> response) {
                result.removeSource(dbSource);
                saveResultAndReInit(response.body());
                Log.e("Network Bound", "onResponse: " + response.isSuccessful());
            }

            @Override
            public void onFailure(@NonNull Call<V> call, @NonNull Throwable t) {
                result.removeSource(dbSource);
                result.addSource(dbSource, newData -> result.setValue(Resource.error(getCustomErrorMessage(t), newData)));
            }
        });
    }

    private String getCustomErrorMessage(Throwable error) {
        if (error instanceof SocketTimeoutException) {
            return ThisApplication.getAppContext().getString(R.string.requestTimeOutError);
        } else if (error instanceof MalformedJsonException) {
            return ThisApplication.getAppContext().getString(R.string.responseMalformedJson);
        } else if (error instanceof IOException) {
            return ThisApplication.getAppContext().getString(R.string.networkError);
        } else if (error instanceof HttpException) {
            return (((HttpException) error).response().message());
        } else {
            return ThisApplication.getAppContext().getString(R.string.unknownError);
        }
    }

    @SuppressLint("StaticFieldLeak")
    @MainThread
    private void saveResultAndReInit(V response) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                saveCallResult(response);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                result.addSource(loadDataFromDb(), newData -> {
                    if (null != newData)
                        result.setValue(Resource.success(newData));
                });
            }
        }.execute();
    }

    @WorkerThread
    protected abstract void saveCallResult(V item);

    @MainThread
    private boolean shouldFetch() {
        return true;
    }

    @NonNull
    @MainThread
    protected abstract LiveData<T> loadDataFromDb();

    @NonNull
    @MainThread
    protected abstract Call<V> createCall();

    public final LiveData<Resource<T>> getAsLiveData() {
        return result;
    }
}
