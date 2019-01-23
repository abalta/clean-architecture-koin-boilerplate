package org.buffer.android.boilerplate.data.browse

import java.util.*

/**
 * Representation for a [City] fetched from an external layer data source
 */
data class WeatherResponse(val data: Data)

data class Data(val request: List<Request>, val weather: List<Weather>)

data class Request(val type: String, val query: String)

data class Weather(val date: Date, val maxtempC: String, val mintempC: String)

