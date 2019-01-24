package org.akce.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.akce.android.boilerplate.cache.db.constants.BufferooConstants

/**
 * Model used solely for the caching of a bufferroo
 */
@Entity(tableName = BufferooConstants.TABLE_NAME)
data class CachedBufferoo(
        @PrimaryKey
        val query: String,
        var type: String
)