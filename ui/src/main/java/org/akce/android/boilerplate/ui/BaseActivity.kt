package org.akce.android.boilerplate.ui

import android.support.v7.app.AppCompatActivity
import org.akce.android.boilerplate.data.browse.WeatherResponse
import org.akce.android.boilerplate.ui.browse.BrowseState

abstract class BaseActivity: AppCompatActivity() {

    fun handleDataState(browseState: BrowseState) {
        when (browseState) {
            is BrowseState.Loading -> setupScreenForLoadingState()
            is BrowseState.Success -> setupScreenForSuccess(browseState.data)
            is BrowseState.Error -> setupScreenForError(browseState.errorMessage)
        }
    }

    abstract fun setupScreenForLoadingState()

    abstract fun setupScreenForSuccess(data: WeatherResponse?)

    abstract fun setupScreenForError(message: String?)

    abstract fun updateListView(data: WeatherResponse)

}