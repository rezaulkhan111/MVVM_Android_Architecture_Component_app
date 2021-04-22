package dev.machine.code.mvvm_application.di.components;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.AndroidSupportInjectionModule;
import dev.machine.code.mvvm_application.ThisApplication;
import dev.machine.code.mvvm_application.di.builder.ActivityBuilderModule;
import dev.machine.code.mvvm_application.di.module.AppModule;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(ThisApplication application);
}
