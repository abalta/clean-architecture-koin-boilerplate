package org.akce.android.boilerplate.data.source

/**
 * Create an instance of a BufferooDataStore
 */
open class WeatherDataStoreFactory(
        private val weatherCacheDataStore: WeatherDataStore,
        private val weatherRemoteDataStore: WeatherDataStore) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(isCached: Boolean): WeatherDataStore {
        if (isCached && !weatherCacheDataStore.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveCacheDataStore(): WeatherDataStore {
        return weatherCacheDataStore
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveRemoteDataStore(): WeatherDataStore {
        return weatherRemoteDataStore
    }

}