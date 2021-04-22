package dev.machine.code.mvvm_application.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dev.machine.code.mvvm_application.viewmodel.BlogViewModel;
import dev.machine.code.mvvm_application.viewmodel.ViewModelFactory;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(BlogViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsBlogViewModel(BlogViewModel blogViewModel);

//    @Binds
//    @IntoMap
//    @ViewModelKey(BlogViewModel.class)
//    @SuppressWarnings("unused")
//    abstract ViewModel bindsEditOrDetailsModel(BlogViewModel blogViewModel);


    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
