package dev.machine.code.mvvm_application.view.activity;

import android.os.Bundle;

import dev.machine.code.mvvm_application.R;
import dev.machine.code.mvvm_application.databinding.ActivityMainBinding;
import dev.machine.code.mvvm_application.utils.FragmentUtils;
import dev.machine.code.mvvm_application.view.base.BaseActivity;
import dev.machine.code.mvvm_application.view.fragment.BlogListFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentUtils.replaceFragment(this, BlogListFragment.newInstance(), R.id.fragContainer, false);
    }
}