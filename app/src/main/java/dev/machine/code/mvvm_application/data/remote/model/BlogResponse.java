package dev.machine.code.mvvm_application.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import dev.machine.code.mvvm_application.data.local.entity.Blog;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
public class BlogResponse {
    @SerializedName("blogs")
    @Expose
    private List<Blog> blogs = null;

    public List<Blog> getBlogs() {
        return blogs;
    }

    @SuppressWarnings("unused")
    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
