package dev.machine.code.mvvm_application.data.remote.repository;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dev.machine.code.mvvm_application.data.local.AppDbHelperRoom;
import dev.machine.code.mvvm_application.data.local.dao.AuthorDao;
import dev.machine.code.mvvm_application.data.local.dao.BlogDao;
import dev.machine.code.mvvm_application.data.local.entity.Author;
import dev.machine.code.mvvm_application.data.local.entity.Blog;
import dev.machine.code.mvvm_application.data.remote.ApiService;
import dev.machine.code.mvvm_application.data.remote.NetworkBoundResource;
import dev.machine.code.mvvm_application.data.remote.Resource;
import dev.machine.code.mvvm_application.data.remote.model.BlogResponse;
import dev.machine.code.mvvm_application.view.callbacks.ResponseListener;
import retrofit2.Call;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
public class BlogRepository {

    private AppDbHelperRoom appDbHelperRoom;
    private ApiService apiService;

    @Inject
    public BlogRepository(AppDbHelperRoom appDbHelperRoom, ApiService apiService) {
        this.appDbHelperRoom = appDbHelperRoom;
        this.apiService = apiService;
    }

    public LiveData<Resource<List<Blog>>> fetchAllBlog() {
        return new NetworkBoundResource<List<Blog>, BlogResponse>() {

            @Override
            protected void saveCallResult(BlogResponse item) {
                if (null != item) {
                    appDbHelperRoom.insertBlogList(item.getBlogs());
                }
            }

            @NonNull
            @Override
            protected LiveData<List<Blog>> loadDataFromDb() {
                return appDbHelperRoom.getLiveDataBlogAndAuthorData();
            }

            @NonNull
            @Override
            protected Call<BlogResponse> createCall() {
                return apiService.getAllBlog();
            }
        }.getAsLiveData();
    }

    public void AddPost(Blog blog) {
        appDbHelperRoom.insertPost(blog);
    }

    public void UpdatePost(Blog blog) {
        appDbHelperRoom.UpdatePost(blog);
    }

    @SuppressLint("CheckResult")
    public void getBlogDetails(String url, ResponseListener responseListener) {
        Blog blog = new Blog();
    }
}
