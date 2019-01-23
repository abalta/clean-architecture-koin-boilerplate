package org.buffer.android.boilerplate.ui.browse

import org.buffer.android.boilerplate.data.browse.WeatherResponse
import org.buffer.android.boilerplate.ui.model.ResourceState

sealed class BrowseState(val resourceState: ResourceState,
                         val data: WeatherResponse? = null,
                         val errorMessage: String? = null) {

    data class Success(private val bufferoos: WeatherResponse): BrowseState(ResourceState.SUCCESS,
            bufferoos)

    data class Error(private val message: String? = null): BrowseState(ResourceState.SUCCESS,
            errorMessage = message)

    object Loading: BrowseState(ResourceState.LOADING)
}