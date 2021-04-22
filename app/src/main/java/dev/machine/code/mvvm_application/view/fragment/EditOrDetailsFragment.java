package dev.machine.code.mvvm_application.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import dev.machine.code.mvvm_application.R;
import dev.machine.code.mvvm_application.data.local.entity.Author;
import dev.machine.code.mvvm_application.data.local.entity.Blog;
import dev.machine.code.mvvm_application.databinding.FragmentEditOrDetailsBinding;
import dev.machine.code.mvvm_application.utils.MultiSpinner.KeyPairBoolData;
import dev.machine.code.mvvm_application.utils.MultiSpinner.SpinnerListener;
import dev.machine.code.mvvm_application.view.base.BaseFragment;
import dev.machine.code.mvvm_application.viewmodel.BlogViewModel;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
public class EditOrDetailsFragment extends BaseFragment<BlogViewModel, FragmentEditOrDetailsBinding> {

    private static Blog previousBlog;

    public EditOrDetailsFragment() {
    }

    final List<String> listItem = new ArrayList<>();
    List<String> strings = new ArrayList<>();
    final List<KeyPairBoolData> listArray1 = new ArrayList<>();

    public static EditOrDetailsFragment newInstance(Blog blog) {
        EditOrDetailsFragment fragment = new EditOrDetailsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        previousBlog = blog;
        return fragment;
    }

    @Override
    protected Class<BlogViewModel> getViewModel() {
        return BlogViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_edit_or_details;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (previousBlog != null && previousBlog.getAuthorId() > 0) {
            dataBinding.btnSavePost.setText("Update Post");
            setPreviousPost();
            dataBinding.btnSavePost.setOnClickListener(view -> {
                Blog blog = initializePost();
                blog.setAuthorId(previousBlog.getAuthor().getId());
                viewModel.UpdatePost(blog);
                getActivity().onBackPressed();
            });
        } else {
            dataBinding.btnSavePost.setText("Add Post");
            dataBinding.btnSavePost.setOnClickListener(view -> {
                Blog blog = initializePost();
                viewModel.AddPost(blog);
                getActivity().onBackPressed();
            });
        }
        return dataBinding.getRoot();
    }

    private Blog initializePost() {
        Blog blog = new Blog();
        Author author = new Author();
        blog.setTitle(String.valueOf(dataBinding.etTitle.getText()));
        blog.setDescription(String.valueOf(dataBinding.etDescription.getText()));
        blog.setCategories(strings);
//        blog.setTitle(String.valueOf(dataBinding.etTitle.getText()));

        author.setId(111);
        author.setName(String.valueOf(dataBinding.etName.getText()));
        author.setProfession(String.valueOf(dataBinding.etProfession.getText()));
        author.setAvatar("https://i.pravatar.cc/250");
        author.setDataType("local");
        blog.setAuthor(author);
        return blog;
    }

    private void setPreviousPost() {
        dataBinding.etTitle.setText(previousBlog.getTitle());
        dataBinding.etDescription.setText(previousBlog.getDescription());
//        dataBinding.etCategory.setText(previousBlog.getTitle());

        dataBinding.etName.setText(previousBlog.getAuthor().getName());
        dataBinding.etProfession.setText(previousBlog.getAuthor().getProfession());
    }


    private void loadMultiSelectForCategory() {
        listItem.add("Business");
        listItem.add("Lifestyle");
        listItem.add("Entertainment");
        listItem.add("Productivity");

        for (int i = 0; i < listItem.size(); i++) {
            KeyPairBoolData keyPairValue = new KeyPairBoolData();
            keyPairValue.setId(i + 1);
            keyPairValue.setName(listItem.get(i));
            keyPairValue.setSelected(false);
            listArray1.add(keyPairValue);
        }

//        dataBinding.spMultipleSelect.setItems(listArray1, -1, new SpinnerListener() {
//            @Override
//            public void onItemsSelected(List<KeyPairBoolData> items) {
//                for (int i = 0; i < items.size(); i++) {
//                    if (items.get(i).isSelected()) {
//                        strings.add(items.get(i).getName());
//                    }
//                }
//            }
//        });
    }
}