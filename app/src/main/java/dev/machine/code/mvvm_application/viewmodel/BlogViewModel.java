package dev.machine.code.mvvm_application.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dev.machine.code.mvvm_application.data.local.entity.Blog;
import dev.machine.code.mvvm_application.data.remote.Resource;
import dev.machine.code.mvvm_application.data.remote.repository.BlogRepository;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
public class BlogViewModel extends ViewModel {

    private final LiveData<Resource<List<Blog>>> resourceBlogLiveData;
    private BlogRepository repository;

    @Inject
    public BlogViewModel(BlogRepository blogRepository) {
        resourceBlogLiveData = blogRepository.fetchAllBlog();
        repository = blogRepository;
    }

    public LiveData<Resource<List<Blog>>> getBlogFromOnline() {
        return resourceBlogLiveData;
    }

    public void AddPost(Blog blog) {
        repository.AddPost(blog);
    }

    public void UpdatePost(Blog blog) {
        repository.UpdatePost(blog);
    }
}
