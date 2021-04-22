package dev.machine.code.mvvm_application.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.jetbrains.annotations.NotNull;

import dev.machine.code.mvvm_application.R;
import dev.machine.code.mvvm_application.data.local.entity.Blog;
import dev.machine.code.mvvm_application.data.remote.Status;
import dev.machine.code.mvvm_application.databinding.FragmentBlogListBinding;
import dev.machine.code.mvvm_application.utils.FragmentUtils;
import dev.machine.code.mvvm_application.view.adapter.BlogAdapter;
import dev.machine.code.mvvm_application.view.base.BaseFragment;
import dev.machine.code.mvvm_application.view.callbacks.ICallBack;
import dev.machine.code.mvvm_application.viewmodel.BlogViewModel;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
public class BlogListFragment extends BaseFragment<BlogViewModel, FragmentBlogListBinding> implements ICallBack {

    public static BlogListFragment newInstance() {
        BlogListFragment fragment = new BlogListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<BlogViewModel> getViewModel() {
        return BlogViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_blog_list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.recyclerView.setAdapter(new BlogAdapter(this));
        dataBinding.fbtnAddPost.setOnClickListener(view -> {
            FragmentUtils.replaceFragment((AppCompatActivity) getActivity(), EditOrDetailsFragment.newInstance(new Blog()), R.id.fragContainer, true);
        });

        return dataBinding.getRoot();
    }

    @Override
    public void onCallBack(Blog blog) {
        FragmentUtils.replaceFragment((AppCompatActivity) getActivity(), EditOrDetailsFragment.newInstance(blog), R.id.fragContainer, true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel.getBlogFromOnline().observe(getActivity(), listResource -> {

            if (null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)) {
                dataBinding.loginProgress.setVisibility(View.GONE);

                dataBinding.setResource(listResource);
            }
            if (null != dataBinding.recyclerView.getAdapter() && dataBinding.recyclerView.getAdapter().getItemCount() > 0) {
                dataBinding.errorText.setVisibility(View.GONE);
            }
        });
    }
}