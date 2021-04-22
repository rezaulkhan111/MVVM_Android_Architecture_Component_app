package dev.machine.code.mvvm_application.view.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
public abstract class BaseActivity<D extends ViewDataBinding> extends AppCompatActivity implements HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;

    @SuppressWarnings("unused")
    public D dataBinding;

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes());
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentAndroidInjector;
    }
}
