package org.buffer.android.boilerplate.remote.mapper

import org.buffer.android.boilerplate.data.browse.Data
import org.buffer.android.boilerplate.remote.model.DataModel

/**
 * Map a [DataModel] to and from a [Data] instance when data is moving between
 * this later and the Data layer
 */
open class BufferooEntityMapper : EntityMapper<DataModel, Data> {

    /**
     * Map an instance of a [DataModel] to a [Data] model
     */
    override fun mapFromRemote(type: DataModel): Data {
        return Data(type.request, type.weather)
    }

}