package dev.machine.code.mvvm_application;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dev.machine.code.mvvm_application.di.components.DaggerAppComponent;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
public class ThisApplication extends Application implements HasActivityInjector {
    private static ThisApplication thisApplication;

    public static ThisApplication getAppContext() {
        return thisApplication;
    }

    private static synchronized void setInstance(ThisApplication app) {
        thisApplication = app;
    }

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        setInstance(this);
    }

    private void initializeComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }

}
