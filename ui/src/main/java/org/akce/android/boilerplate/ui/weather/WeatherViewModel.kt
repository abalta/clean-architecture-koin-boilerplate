package org.akce.android.boilerplate.ui.weather

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import org.akce.android.boilerplate.data.browse.interactor.GetCities
import org.akce.android.boilerplate.data.browse.interactor.RemoveAll
import org.akce.android.boilerplate.data.browse.interactor.SaveCity
import org.akce.android.boilerplate.ui.browse.BrowseState

class WeatherViewModel(val getCities: GetCities, val saveCity: SaveCity, val removeAll: RemoveAll) : ViewModel() {

    private val weatherLiveData: MutableLiveData<BrowseState> = MutableLiveData()
    private val saveCityLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val removeAllLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private var disposable: Disposable? = null

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    fun getWeatherLiveData(): LiveData<BrowseState> {
        return weatherLiveData
    }

    fun getCityLiveData(): LiveData<Boolean> {
        return saveCityLiveData
    }

    fun getRemoveAllLiveData(): LiveData<Boolean> {
        return removeAllLiveData
    }

    fun getWeather(q: String) {
        weatherLiveData.postValue(BrowseState.Loading)
        disposable = getCities.execute(q)
                .subscribe({
                    weatherLiveData.postValue(BrowseState.Success(it))
                }, {
                    weatherLiveData.postValue(BrowseState.Error(it.message))
                })
    }

    fun saveCity(name: String) {
        disposable = saveCity.execute(name)
                .subscribe({
                    saveCityLiveData.postValue(true)
                }, {
                    saveCityLiveData.postValue(false)
                })
    }

    fun removeAll() {
        disposable = removeAll.execute(Unit)
                .subscribe({
                    removeAllLiveData.postValue(true)
                },
                        {
                            removeAllLiveData.postValue(false)
                        })
    }

}

