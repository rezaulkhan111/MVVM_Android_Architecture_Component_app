package dev.machine.code.mvvm_application.view.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
public abstract class BaseFragment<V extends ViewModel, D extends ViewDataBinding> extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public V viewModel;

    protected D dataBinding;

    protected abstract Class<V> getViewModel();

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        return dataBinding.getRoot();
    }
}
