package dev.machine.code.mvvm_application.data.local;

import androidx.lifecycle.LiveData;

import java.util.List;

import dev.machine.code.mvvm_application.data.local.entity.Author;
import dev.machine.code.mvvm_application.data.local.entity.Blog;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
public interface DbHelperRoom {
    List<Blog> getBlogAndAuthorData();

    LiveData<List<Blog>> getLiveDataBlogAndAuthorData();

    void insertBlogList(List<Blog> blogList);

    LiveData<List<Blog>> getAllBlog();

    void insertPost(Blog blog);
    
    void UpdatePost(Blog blog);

    void insertAuthor(Author author);

    LiveData<Author> getAllAuthor();

    Author getAuthorByBlogId(int id);
}
