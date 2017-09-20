package com.foo.umbrella.di;

import com.foo.umbrella.ui.WeatherReportListActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract WeatherReportListActivity weatherReportListActivity();

}
