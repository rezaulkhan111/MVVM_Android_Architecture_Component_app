package dev.machine.code.mvvm_application.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dev.machine.code.mvvm_application.view.fragment.BlogListFragment;
import dev.machine.code.mvvm_application.view.fragment.EditOrDetailsFragment;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
@Module
public abstract class FragmentBuilderModule {
    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract BlogListFragment contributeBlogListFragment();

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract EditOrDetailsFragment contributeEditOrDetailsFragment();
}
