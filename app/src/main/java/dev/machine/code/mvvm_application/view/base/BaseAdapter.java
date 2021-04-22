package dev.machine.code.mvvm_application.view.base;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
public abstract class BaseAdapter<T extends RecyclerView.ViewHolder, D> extends RecyclerView.Adapter<T> {

    public abstract void setData(List<D> data);

}
