package org.akce.android.boilerplate.ui.browse

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.disposables.Disposable
import org.akce.android.boilerplate.data.browse.Request
import org.akce.android.boilerplate.data.browse.interactor.GetCities
import org.akce.android.boilerplate.data.browse.interactor.GetSavedCities

class MainViewModel(val getCities: GetCities, val getSavedCities: GetSavedCities) : ViewModel() {

    private val cityLiveData: MutableLiveData<BrowseState> = MutableLiveData()
    private val savedLiveData: MutableLiveData<List<Request>> = MutableLiveData()
    private var disposable: Disposable? = null

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    fun getCityLiveData(): LiveData<BrowseState> {
        return cityLiveData
    }

    fun getSavedLiveData(): LiveData<List<Request>> {
        return savedLiveData
    }

    fun findCity(q: String) {
        cityLiveData.postValue(BrowseState.Loading)
        disposable = getCities.execute(q)
                .subscribe({
                    cityLiveData.postValue(BrowseState.Success(it))
                }, {
                    cityLiveData.postValue(BrowseState.Error (it.message))
                })
    }

    fun getSavedCities() {
        disposable = getSavedCities.execute(Unit)
                .subscribe({
                    savedLiveData.postValue(it)
                }, {
                    Log.e("FETCH ERROR", it.message)
                })
    }
}