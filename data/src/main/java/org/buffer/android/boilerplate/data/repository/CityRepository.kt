package org.buffer.android.boilerplate.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import org.buffer.android.boilerplate.data.browse.Request
import org.buffer.android.boilerplate.data.browse.WeatherResponse

interface CityRepository {

    open fun clearBufferoos(): Completable

    open fun saveBufferoos(cities: List<Request>): Completable

    open fun getCities(): Flowable<List<Request>>

    open fun findCity(q: String?): Flowable<WeatherResponse>

}