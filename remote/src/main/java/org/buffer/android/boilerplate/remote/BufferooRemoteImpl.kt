package org.buffer.android.boilerplate.remote

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.buffer.android.boilerplate.data.browse.Data
import org.buffer.android.boilerplate.data.browse.Request
import org.buffer.android.boilerplate.data.browse.WeatherResponse
import org.buffer.android.boilerplate.data.source.BufferooDataStore
import org.buffer.android.boilerplate.remote.mapper.BufferooEntityMapper

/**
 * Remote implementation for retrieving Bufferoo instances. This class implements the
 * [BufferooRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class BufferooRemoteImpl constructor(private val cityService: CityService,
                                     private val entityMapper: BufferooEntityMapper)
    :BufferooDataStore {

    override fun findCity(q: String): Flowable<WeatherResponse> {
        return cityService.getData("9f36c5173cc24d58a57213320192201", q!!, "json", "5")
    }

    /**
     * Retrieve a list of [City] instances from the [CityService].
     */
    override fun getCities(): Flowable<List<Request>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearBufferoos(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveBufferoos(data: List<Request>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCached(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLastCacheTime(lastCache: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isExpired(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}