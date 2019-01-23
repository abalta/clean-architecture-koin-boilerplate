package org.buffer.android.boilerplate.ui.browse

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import org.buffer.android.boilerplate.data.browse.interactor.GetCities

class BrowseBufferoosViewModel(val getCities: GetCities) : ViewModel() {

    private val bufferoosLiveData: MutableLiveData<BrowseState> = MutableLiveData()
    private var disposable: Disposable? = null

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    fun getBufferoos(): LiveData<BrowseState> {
        return bufferoosLiveData
    }

    fun findCity(q: String) {
        bufferoosLiveData.postValue(BrowseState.Loading)
        disposable = getCities.execute(q)
                .subscribe({
                    bufferoosLiveData.postValue(BrowseState.Success(it))
                }, {
                    bufferoosLiveData.postValue(BrowseState.Error (it.message))
                })
    }
}