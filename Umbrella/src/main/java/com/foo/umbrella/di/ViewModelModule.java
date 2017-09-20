package com.foo.umbrella.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.foo.umbrella.data.viewmodel.WeatherListViewModel;
import com.foo.umbrella.data.viewmodel.WeatherViewModelFactory;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherListViewModel.class)
    abstract ViewModel bindWeatherListViewModel(WeatherListViewModel weatherListViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(WeatherViewModelFactory weatherViewModelFactory);
}
