package dev.machine.code.mvvm_application.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dev.machine.code.mvvm_application.data.local.entity.Author;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
@Dao
public interface AuthorDao {

    @Query("SELECT * FROM Author")
    LiveData<List<Author>> loadAllAuthor();

    @Query("SELECT * FROM Author")
    List<Author> loadListAuthor();

    @Insert
    void saveAuthor(Author author);

    @Query("SELECT * FROM Author where id=:id")
    Author getAuthorByBlogId(int id);


    @Query("DELETE FROM Author")
    int DeleteAllAuthor();

    @Update
    void UpdateAuthor(Author author);
}
