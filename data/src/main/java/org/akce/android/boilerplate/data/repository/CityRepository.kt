package org.akce.android.boilerplate.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import org.akce.android.boilerplate.data.browse.Request
import org.akce.android.boilerplate.data.browse.WeatherResponse

interface CityRepository {

    open fun clearBufferoos(): Completable

    open fun saveCity(cities: Request): Completable

    open fun deleteCity(city: Request): Completable

    open fun getCities(): Flowable<List<Request>>

    open fun findCity(q: String?): Flowable<WeatherResponse>

}