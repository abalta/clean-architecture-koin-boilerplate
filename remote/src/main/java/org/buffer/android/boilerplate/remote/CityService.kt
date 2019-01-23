package org.buffer.android.boilerplate.remote

import io.reactivex.Flowable
import org.buffer.android.boilerplate.data.browse.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Defines the abstract methods used for interacting with the Bufferoo API
 */
interface CityService {

    @GET("premium/v1/weather.ashx")
    fun getData(@Query("key") key: String,
                @Query("q") q: String,
                @Query("format") format: String,
                @Query("num_of_days") numOfDays: String): Flowable<WeatherResponse>

}