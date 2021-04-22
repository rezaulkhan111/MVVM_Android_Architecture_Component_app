package dev.machine.code.mvvm_application.data.remote;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalHttpUrl = originalRequest.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", "")
                .build();

        Request request = originalRequest.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
