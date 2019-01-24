package org.akce.android.boilerplate.data.source

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.akce.android.boilerplate.data.browse.Request
import org.akce.android.boilerplate.data.browse.WeatherResponse

/**
 * Interface defining methods for the data operations related to Bufferroos.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface WeatherDataStore {

    fun clearBufferoos(): Completable

    fun deleteCity(city: Request): Completable

    fun saveBufferoos(cities: Request): Completable

    fun getCities(): Flowable<List<Request>>

    fun findCity(q: String): Flowable<WeatherResponse>

    fun isCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long)

    fun isExpired(): Boolean

}