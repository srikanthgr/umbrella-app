package com.foo.umbrella;

import android.app.Activity;
import android.app.Application;

import com.foo.umbrella.di.DaggerAppComponent;
import com.jakewharton.threetenabp.AndroidThreeTen;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class UmbrellaApp extends Application implements HasActivityInjector {

  @Inject
  DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

  @Override public void onCreate() {
    super.onCreate();
    AndroidThreeTen.init(this);
    DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this);
  }
    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
