package org.buffer.android.boilerplate.ui.di

import android.arch.persistence.room.Room
import org.buffer.android.boilerplate.cache.BufferooCacheImpl
import org.buffer.android.boilerplate.cache.PreferencesHelper
import org.buffer.android.boilerplate.cache.db.BufferoosDatabase
import org.buffer.android.boilerplate.cache.mapper.BufferooEntityMapper
import org.buffer.android.boilerplate.data.CityDataRepository
import org.buffer.android.boilerplate.data.browse.interactor.GetCities
import org.buffer.android.boilerplate.data.executor.JobExecutor
import org.buffer.android.boilerplate.data.executor.PostExecutionThread
import org.buffer.android.boilerplate.data.executor.ThreadExecutor
import org.buffer.android.boilerplate.data.repository.CityRepository
import org.buffer.android.boilerplate.data.source.BufferooDataStore
import org.buffer.android.boilerplate.data.source.BufferooDataStoreFactory
import org.buffer.android.boilerplate.remote.BufferooRemoteImpl
import org.buffer.android.boilerplate.remote.BufferooServiceFactory
import org.buffer.android.boilerplate.ui.BuildConfig
import org.buffer.android.boilerplate.ui.UiThread
import org.buffer.android.boilerplate.ui.browse.BrowseAdapter
import org.buffer.android.boilerplate.ui.browse.BrowseBufferoosViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module(override=true) {

    single { PreferencesHelper(androidContext()) }

    factory { org.buffer.android.boilerplate.remote.mapper.BufferooEntityMapper() }

    single { JobExecutor() as ThreadExecutor }
    single { UiThread() as PostExecutionThread }

    single { Room.databaseBuilder(androidContext(),
            BufferoosDatabase::class.java, "bufferoos.db")
            .build() }
    factory { get<BufferoosDatabase>().cachedBufferooDao() }

    factory<BufferooDataStore>("remote") { BufferooRemoteImpl(get(), get()) }
    factory<BufferooDataStore>("local") { BufferooCacheImpl(get(), get(), get()) }
    factory { BufferooDataStoreFactory(get("local"), get("remote")) }

    factory { BufferooEntityMapper() }
    factory { BufferooServiceFactory.makeBuffeoorService(BuildConfig.DEBUG) }

    factory<CityRepository> { CityDataRepository(get()) }
}

val browseModule = module("Browse", override = true) {
    factory { BrowseAdapter() }
    factory { GetCities(get(), get(), get()) }
    viewModel { BrowseBufferoosViewModel(get()) }
}