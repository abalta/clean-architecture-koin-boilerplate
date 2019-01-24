package org.akce.android.boilerplate.data.browse

/**
 * Representation for a [City] fetched from an external layer data source
 */
data class WeatherResponse(val data: Data)

data class Data(val request: List<Request>, val weather: List<Weather>)

data class Request(val type: String, val query: String)

data class Weather(val date: String, val maxtempC: String, val mintempC: String)

