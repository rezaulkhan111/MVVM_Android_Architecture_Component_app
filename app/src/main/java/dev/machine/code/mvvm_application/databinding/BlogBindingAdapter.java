package dev.machine.code.mvvm_application.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.machine.code.mvvm_application.data.remote.Resource;
import dev.machine.code.mvvm_application.view.base.BaseAdapter;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
final class BlogBindingAdapter {

    private BlogBindingAdapter() {
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = "resource")
    public static void setResource(RecyclerView recyclerView, Resource resource) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null)
            return;

        if (resource == null || resource.data == null)
            return;

        if (adapter instanceof BaseAdapter) {
            ((BaseAdapter) adapter).setData((List) resource.data);
        }
    }
}
