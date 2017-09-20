package com.foo.umbrella.ui;

import android.app.Activity;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foo.umbrella.R;
import com.foo.umbrella.data.model.ResponseModel.HourlyForecast;
import com.foo.umbrella.data.model.ResponseModel.WeatherItemModel;
import com.foo.umbrella.data.viewmodel.WeatherListViewModel;
import com.foo.umbrella.ui.adapter.WeatherListAdapter;
import com.foo.umbrella.utils.Utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class WeatherReportListActivity extends AppCompatActivity implements LifecycleRegistryOwner, HasActivityInjector {
    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Inject
    WeatherListViewModel weatherListViewModel;
    RecyclerView recyclerView;
    WeatherListAdapter adapter;
    final int[] count = {0};
    TextView location;
    RelativeLayout lLayout;
    TextView cityTemperature;
    Button button;
    Map<String, List<HourlyForecast>> weatherMaps;
    ArrayList<WeatherItemModel> weatherItemModelList;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPreference();
        init();
        initRecycler();
        weatherListViewModel.setZipCode("03060");
        observeViewModel(weatherListViewModel);
        button.setOnClickListener(view -> {
            Intent intentSetPref = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivityForResult(intentSetPref, 0);

        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //checkPreference();
    }

    /**
     * Observe the view model class for any changes in the list and update the adapter.
     *
     * @param viewModel CategoriesViewModel
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void observeViewModel(WeatherListViewModel viewModel) {
        // Update the list when the data changes

        viewModel.getWeatherObservable().observe(this, weatherList -> {
            if (weatherList != null) {

                location.setText(weatherList.body().getCurrentObservation().getDisplayLocation().getFull());

                if (weatherList.body().getCurrentObservation().getTempF() > 60) {
                    lLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.weather_warm));
                } else {
                    lLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.weather_cool));
                }

                String temp = Utils.getSharedPreferenceUnits(this).equals("0") ? String.valueOf(weatherList.body().getCurrentObservation().getTempC()) : String.valueOf(weatherList.body().getCurrentObservation().getTempF());
                cityTemperature.setText(temp + (char) 0x00B0);
                //Group the temperature values based on days
                weatherMaps =
                        weatherList.body().getHourlyForecast().stream().collect(Collectors.groupingBy(w -> w.getFCTTIME().getMday()));

                //Iterate through the list of keys
                for (Map.Entry<String, List<HourlyForecast>> entry : weatherMaps.entrySet()) {
                    findOccurrence(entry.getValue());
                }
            }

        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void findOccurrence(List<HourlyForecast> hourlyForecasts) {
        //find the largest and small values of the temperature currently checking through metric measurements
        HourlyForecast minHourlyItem = hourlyForecasts.stream()
                .min(Comparator.comparing(i -> i.getTemp().getEnglish())).get();
        HourlyForecast highestHourlyItem = hourlyForecasts.stream()
                .max(Comparator.comparing(i -> i.getTemp().getEnglish())).get();
        weatherItemModelList = new ArrayList<>();
        WeatherItemModel weatherItemModel;
        weatherItemModelList.clear();
        for (int i = 0; i < hourlyForecasts.size(); i++) {
            weatherItemModel = new WeatherItemModel();
            weatherItemModel.setTime(hourlyForecasts.get(i).getFCTTIME().getCivil());
            weatherItemModel.setEnglish(hourlyForecasts.get(i).getTemp().getEnglish());
            weatherItemModel.setMetric(hourlyForecasts.get(i).getTemp().getMetric());
            weatherItemModel.setFahrenheit(hourlyForecasts.get(i).getTemp().getEnglish());
            weatherItemModel.setCondition(hourlyForecasts.get(i).getCondition());
            if (minHourlyItem.getFCTTIME().getPretty().equals(hourlyForecasts.get(i).getFCTTIME().getPretty())) {
                weatherItemModel.setCoolTint(true);
            } else if (highestHourlyItem.getFCTTIME().getPretty().equals(hourlyForecasts.get(i).getFCTTIME().getPretty())) {
                weatherItemModel.setHotTint(true);
            } else if (minHourlyItem.getFCTTIME().getPretty().equals(highestHourlyItem.getFCTTIME().getPretty())) {
                weatherItemModel.setDefaultTint(true);
            } else {
                weatherItemModel.setDefaultTint(true);
            }
            weatherItemModelList.add(weatherItemModel);
        }

        if (count[0] == 0) {
            adapter.addGridItem(weatherItemModelList);
            count[0]++;
        } else if (count[0] == 1) {
            adapter.addHourlyGridView(weatherItemModelList);
            count[0]++;
        }


    }

    private void init() {
        location = (TextView) findViewById(R.id.location);
        lLayout = (RelativeLayout) findViewById(R.id.background);
        cityTemperature = (TextView) findViewById(R.id.temperature);
        recyclerView = (RecyclerView) findViewById(R.id.rcv_list);
        button = (Button) findViewById(R.id.settings);
    }

    private void initRecycler() {
        GridLayoutManager glm = new GridLayoutManager(getApplicationContext(), 4);
        adapter = new WeatherListAdapter(this);
        adapter.setSpanCount(4);
        glm.setSpanSizeLookup(adapter.getSpanSizeLookup());
        recyclerView.addItemDecoration(adapter.getItemDecorationManager());
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void checkPreference() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String zipCode = sharedPreferences.getString(getString(R.string.key_zip_code), null);
        if (zipCode == null) {
            Intent intentSetPref = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivityForResult(intentSetPref, 0);
        } else {
            if (weatherMaps != null && weatherItemModelList != null) {
                weatherMaps.clear();
                weatherItemModelList.clear();
                count[0] = 0;
            }

        }
    }


    @Override
    public LifecycleRegistry getLifecycle() {
        return mRegistry;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

}
