package org.buffer.android.boilerplate.cache.mapper

import org.buffer.android.boilerplate.cache.model.CachedBufferoo
import org.buffer.android.boilerplate.data.browse.Request

/**
 * Map a [CachedBufferoo] instance to and from a [City] instance when data is moving between
 * this later and the Data layer
 */
open class BufferooEntityMapper :
        EntityMapper<CachedBufferoo, Request> {

    /**
     * Map a [City] instance to a [CachedBufferoo] instance
     */
    override fun mapToCached(type: Request): CachedBufferoo {
        return CachedBufferoo(type.type, type.query)
    }

    /**
     * Map a [CachedBufferoo] instance to a [City] instance
     */
    override fun mapFromCached(type: CachedBufferoo): Request {
        return Request(type.type, type.query)
    }

}