package org.akce.android.boilerplate.data

import io.reactivex.Completable
import io.reactivex.Flowable
import org.akce.android.boilerplate.data.browse.Request
import org.akce.android.boilerplate.data.browse.WeatherResponse
import org.akce.android.boilerplate.data.repository.CityRepository
import org.akce.android.boilerplate.data.source.WeatherDataStoreFactory

/**
 * Provides an implementation of the [CityRepository] interface for communicating to and from
 * data sources
 */
open class CityDataRepository(private val factory: WeatherDataStoreFactory) :
        CityRepository {
    override fun deleteCity(city: Request): Completable {
        return factory.retrieveCacheDataStore().clearBufferoos()
    }

    override fun findCity(q: String?): Flowable<WeatherResponse> {
        return factory.retrieveRemoteDataStore().findCity(q!!)
    }

    override fun clearBufferoos(): Completable {
        return factory.retrieveCacheDataStore().clearBufferoos()
    }

    override fun saveCity(cities: Request): Completable {
        return factory.retrieveCacheDataStore().saveBufferoos(cities)
    }

    override fun getCities(): Flowable<List<Request>> {
        return factory.retrieveCacheDataStore().isCached()
                .flatMapPublisher {
                    factory.retrieveCacheDataStore().getCities()
                }
    }

}