package dev.machine.code.mvvm_application.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dev.machine.code.mvvm_application.data.local.entity.Author;
import dev.machine.code.mvvm_application.data.local.entity.Blog;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
@Dao
public interface BlogDao {

    @Query("SELECT * FROM Blog")
    LiveData<List<Blog>> loadAllBlog();

    @Query("SELECT * FROM Blog")
    List<Blog> getListAllBlog();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBlogList(List<Blog> blogList);

    @Insert
    long Insert(Blog blog);

    @Query("DELETE FROM Blog")
    int DeleteAllBlog();

//    @Query("SELECT * FROM Blog where authorId=:authorId")
//    Blog DeleteBlogWithOutDataType(int authorId);

    @Query("SELECT * FROM Blog where authorId=:authorId")
    Blog getBlogByAuthorId(int authorId);

    @Update
    void UpdateBlog(Blog blog);
}
