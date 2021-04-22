package dev.machine.code.mvvm_application.di.module;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.MediatorLiveData;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dev.machine.code.mvvm_application.data.local.AppDataBase;
import dev.machine.code.mvvm_application.data.local.AppDbHelperRoom;
import dev.machine.code.mvvm_application.data.local.dao.AuthorDao;
import dev.machine.code.mvvm_application.data.local.dao.BlogDao;
import dev.machine.code.mvvm_application.data.local.entity.Author;
import dev.machine.code.mvvm_application.data.local.entity.Blog;
import dev.machine.code.mvvm_application.data.remote.ApiConstants;
import dev.machine.code.mvvm_application.data.remote.ApiService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    ApiService provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    AppDbHelperRoom provideBlogDatabase(Application application) {
//        return Room.databaseBuilder(application, AppDataBase.class, "BlogDataBase.db").build();
        return new AppDbHelperRoom(application);
    }

//    @Provides
//    @Singleton
//    BlogDao provideBlogDao(AppDataBase appDataBase) {
//        return appDataBase.blogDao();
//    }
//
//    @Provides
//    @Singleton
//    AuthorDao provideAuthorDao(AppDataBase appDataBase) {
//        return appDataBase.authorDao();
//    }

//    @Provides
//    @Singleton
//    MediatorLiveData<List<Blog>> provideMediatorLiveData() {
//        return new MediatorLiveData<>();
//    }

//    @Provides
//    @Singleton
//    @SuppressLint("StaticFieldLeak")
//    public MediatorLiveData<List<Blog>> provideExistBlog(AppDataBase appDataBase) {
//        MediatorLiveData<List<Blog>> listMediatorLiveData = new MediatorLiveData<>();
////        final List<Blog>[] fblogList = new List[]{new ArrayList<>()};
//        List<Blog> blogList = new ArrayList<>();
//        List<Blog> existBlog = appDataBase.blogDao().getListAllBlog();
//        for (Blog itemB : existBlog) {
//            Author author = appDataBase.authorDao().getAuthorByBlogId(itemB.getBlogId());
//            itemB.setAuthor(author);
//            blogList.add(itemB);
//        }
//        listMediatorLiveData.setValue(blogList);
//        return listMediatorLiveData;
//    }
}
