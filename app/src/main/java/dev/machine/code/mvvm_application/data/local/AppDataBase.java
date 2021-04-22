package dev.machine.code.mvvm_application.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import dev.machine.code.mvvm_application.data.local.dao.AuthorDao;
import dev.machine.code.mvvm_application.data.local.dao.BlogDao;
import dev.machine.code.mvvm_application.data.local.entity.Author;
import dev.machine.code.mvvm_application.data.local.entity.Blog;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
@Database(entities = {Blog.class, Author.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract AuthorDao authorDao();

    public abstract BlogDao blogDao();
}
