package com.foo.umbrella.data.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;
import com.foo.umbrella.data.model.ResponseModel.WeatherResponse;
import com.foo.umbrella.data.remote.repository.WeatherRepository;
import javax.inject.Inject;
import retrofit2.Response;


public class WeatherListViewModel extends AndroidViewModel {
    private static final MutableLiveData ABSENT = new MutableLiveData();
    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    private final MutableLiveData<String> zipCode;

    private final LiveData<Response<WeatherResponse>> categoriesListObservable;

    @Inject
    public WeatherListViewModel(@NonNull WeatherRepository weatherRepository, @NonNull Application application) {
        super(application);
        this.zipCode = new MutableLiveData<>();

        categoriesListObservable = Transformations.switchMap(zipCode, input -> weatherRepository.getWeatherResponse(zipCode.getValue()));
    }

    /**
     * Expose the LiveData categories query so the UI can observe it.
     */
    public LiveData<Response<WeatherResponse>> getWeatherObservable() {
        return categoriesListObservable;
    }

    public void setZipCode(String zipCode) {
        this.zipCode.setValue(zipCode);
    }

}
