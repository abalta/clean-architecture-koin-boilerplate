package org.buffer.android.boilerplate.remote.model

import org.buffer.android.boilerplate.data.browse.Request
import org.buffer.android.boilerplate.data.browse.Weather

/**
 * Representation for a [DataModel] fetched from the API
 */
class DataModel(val request: List<Request>, val weather: List<Weather>)