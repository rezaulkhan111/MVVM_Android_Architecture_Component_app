package dev.machine.code.mvvm_application.data.remote;

import dev.machine.code.mvvm_application.data.remote.model.BlogResponse;
import retrofit2.Call;
import retrofit2.http.GET;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
public interface ApiService {

    @GET("simple-blog-api/db")
    Call<BlogResponse> getAllBlog();
}
