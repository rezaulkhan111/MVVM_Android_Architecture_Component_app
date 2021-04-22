package dev.machine.code.mvvm_application.di.builder;

import androidx.fragment.app.Fragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dev.machine.code.mvvm_application.view.activity.MainActivity;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
@Module
public abstract class ActivityBuilderModule {
    @SuppressWarnings("unused")
    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MainActivity mainActivity();
}
