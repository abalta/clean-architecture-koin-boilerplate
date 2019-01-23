package org.buffer.android.boilerplate.data

import io.reactivex.Completable
import io.reactivex.Flowable
import org.buffer.android.boilerplate.data.browse.Data
import org.buffer.android.boilerplate.data.browse.Request
import org.buffer.android.boilerplate.data.browse.WeatherResponse
import org.buffer.android.boilerplate.data.repository.CityRepository
import org.buffer.android.boilerplate.data.source.BufferooDataStoreFactory

/**
 * Provides an implementation of the [CityRepository] interface for communicating to and from
 * data sources
 */
open class CityDataRepository(private val factory: BufferooDataStoreFactory) :
        CityRepository {

    override fun findCity(q: String?): Flowable<WeatherResponse> {
        return factory.retrieveRemoteDataStore().findCity(q!!)
    }

    override fun clearBufferoos(): Completable {
        return factory.retrieveCacheDataStore().clearBufferoos()
    }

    override fun saveBufferoos(cities: List<Request>): Completable {
        return factory.retrieveCacheDataStore().saveBufferoos(cities)
    }

    override fun getCities(): Flowable<List<Request>> {
        return factory.retrieveCacheDataStore().isCached()
                .flatMapPublisher {
                    factory.retrieveDataStore(it).getCities()
                }
                .flatMap {
                    saveBufferoos(it).toSingle { it }.toFlowable()
                }
    }

}