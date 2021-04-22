package dev.machine.code.mvvm_application.data.local;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import dev.machine.code.mvvm_application.data.local.entity.Author;
import dev.machine.code.mvvm_application.data.local.entity.Blog;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
public class AppDbHelperRoom implements DbHelperRoom {

    private final AppDataBase db;
    private MediatorLiveData<List<Blog>> listMediatorLiveData;

    public AppDbHelperRoom(Application application) {
        db = Room.databaseBuilder(application, AppDataBase.class, "BlogDataBase.db").build();
        listMediatorLiveData = new MediatorLiveData<>();
    }

    @Override
    public List<Blog> getBlogAndAuthorData() {
        List<Blog> blogList = new ArrayList<>();
        List<Blog> existBlog = db.blogDao().getListAllBlog();
        for (Blog itemB : existBlog) {
            Author author = db.authorDao().getAuthorByBlogId(itemB.getId());
            itemB.setAuthor(author);
            blogList.add(itemB);
        }
        return null;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public LiveData<List<Blog>> getLiveDataBlogAndAuthorData() {
        new AsyncTask<Void, Void, List<Blog>>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected List<Blog> doInBackground(Void... voids) {
                List<Blog> blogList = new ArrayList<>();
//                List<Blog> existBlog = db.blogDao().getListAllBlog();
                List<Author> existAuthor = db.authorDao().loadListAuthor();
                for (Author author : existAuthor) {
                    Blog blog = db.blogDao().getBlogByAuthorId(author.getId());
                    blog.setAuthor(author);
                    blogList.add(blog);
                }

//                for (Blog itemB : existBlog) {
//                    Author author = db.authorDao().getAuthorByBlogId(itemB.getId());
//                    itemB.setAuthor(author);
//                    blogList.add(itemB);
//                }
                return blogList;
            }

            @Override
            protected void onPostExecute(List<Blog> blogList) {
//                super.onPostExecute(blogList);
                listMediatorLiveData.setValue(blogList);
            }
        }.execute();
        return listMediatorLiveData;
    }


    @SuppressLint("StaticFieldLeak")
    @Override
    public void insertBlogList(List<Blog> blogList) {
        new AsyncTask<List<Blog>, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(List<Blog>... voids) {
//                db.blogDao().DeleteAllBlog();
//                db.authorDao().DeleteAllAuthor();
//
//                db.blogDao().insertBlogList(blogList);
                for (Blog itemBlog : voids[0]) {
//                    Author author = itemBlog.getAuthor();
//                    author.setBlogId(itemBlog.getId());
                    itemBlog.setAuthorId(itemBlog.getAuthor().getId());

                    db.blogDao().Insert(itemBlog);
                    itemBlog.getAuthor().setDataType("");
                    db.authorDao().saveAuthor(itemBlog.getAuthor());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void blogList) {
                super.onPostExecute(blogList);
            }
        }.execute(blogList);
    }


    @Override
    public LiveData<List<Blog>> getAllBlog() {
        return db.blogDao().loadAllBlog();
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void insertPost(Blog blog) {
        new AsyncTask<Blog, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Blog... blog) {
                db.authorDao().saveAuthor(blog[0].getAuthor());
                blog[0].setAuthorId(blog[0].getAuthor().getId());
                db.blogDao().Insert(blog[0]);
                return null;
            }

            @Override
            protected void onPostExecute(Void blogList) {
                super.onPostExecute(blogList);
            }
        }.execute(blog);
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void UpdatePost(Blog blog) {
        new AsyncTask<Blog, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Blog... blog) {
                db.authorDao().UpdateAuthor(blog[0].getAuthor());
                db.blogDao().UpdateBlog(blog[0]);
                return null;
            }

            @Override
            protected void onPostExecute(Void blogList) {
                super.onPostExecute(blogList);
            }
        }.execute(blog);
    }

    @Override
    public void insertAuthor(Author author) {
        db.authorDao().saveAuthor(author);
    }

    @Override
    public LiveData<Author> getAllAuthor() {
        return null;
    }

    @Override
    public Author getAuthorByBlogId(int id) {
        return db.authorDao().getAuthorByBlogId(id);
    }
}
