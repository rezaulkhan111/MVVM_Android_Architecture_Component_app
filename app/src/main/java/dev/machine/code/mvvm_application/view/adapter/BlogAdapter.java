package dev.machine.code.mvvm_application.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dev.machine.code.mvvm_application.R;
import dev.machine.code.mvvm_application.common.Constants;
import dev.machine.code.mvvm_application.data.local.entity.Blog;
import dev.machine.code.mvvm_application.databinding.ItemBlogViewBinding;
import dev.machine.code.mvvm_application.view.base.BaseAdapter;
import dev.machine.code.mvvm_application.view.callbacks.ICallBack;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
public class BlogAdapter extends BaseAdapter<BlogAdapter.BlogViewHolder, Blog> {

    private List<Blog> blogList;

    private final ICallBack iCallBack;

    public BlogAdapter(ICallBack iCallBack) {
        this.blogList = new ArrayList<>();
        this.iCallBack = iCallBack;
    }

    @Override
    public void setData(List<Blog> data) {
        this.blogList = data;
        notifyDataSetChanged();
    }

    public void setBlogList(List<Blog> data) {
        this.blogList = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return BlogViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, iCallBack);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        Blog blog = blogList.get(position);
        holder.onBind(blog);
    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    static class BlogViewHolder extends RecyclerView.ViewHolder {
        public static BlogViewHolder create(LayoutInflater inflater, ViewGroup parent, ICallBack callBack) {
            return new BlogViewHolder(ItemBlogViewBinding.inflate(inflater, parent, false), callBack);
        }

        final ItemBlogViewBinding binding;

        public BlogViewHolder(@NonNull ItemBlogViewBinding binding, ICallBack callBack) {
            super(binding.getRoot());
            this.binding = binding;
            binding.ivEditPost.setOnClickListener(view -> {
                callBack.onCallBack(binding.getBlog());
            });
        }

        public void onBind(Blog blog) {
            binding.setBlog(blog);
            binding.executePendingBindings();
            binding.ivEditPost.setEnabled(false);
            binding.ivEditPost.setVisibility(View.GONE);
            if (!blog.getAuthor().getDataType().equals("")) {
                binding.ivEditPost.setEnabled(true);
                binding.ivEditPost.setVisibility(View.VISIBLE);
                binding.ivEditPost.setImageResource(R.drawable.ic_baseline_edit_24);
            }
            try {
                Constants.LoadImage(blog.getAuthor().getAvatar(), binding.profileImage);
                Constants.LoadImage(blog.getCoverPhoto(), binding.postImage);
            } catch (Exception e) {
                Log.e("message", e.getMessage());
            }
        }
    }
}
