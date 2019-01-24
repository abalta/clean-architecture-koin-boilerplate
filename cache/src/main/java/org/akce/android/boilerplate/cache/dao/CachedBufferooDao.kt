package org.akce.android.boilerplate.cache.dao

import android.arch.persistence.room.*
import org.akce.android.boilerplate.cache.db.constants.BufferooConstants
import org.akce.android.boilerplate.cache.model.CachedBufferoo

@Dao
abstract class CachedBufferooDao {

    @Query(BufferooConstants.QUERY_BUFFEROOS)
    abstract fun getBufferoos(): List<CachedBufferoo>

    @Query(BufferooConstants.DELETE_ALL_BUFFEROOS)
    abstract fun clearBufferoos()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertBufferoo(cachedBufferoo: CachedBufferoo)

    @Delete
    abstract fun deleteCity(cachedBufferoo: CachedBufferoo)


}