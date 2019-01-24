package org.akce.android.boilerplate.ui.di

import android.arch.persistence.room.Room
import org.akce.android.boilerplate.cache.WeatherCacheImpl
import org.akce.android.boilerplate.cache.BuildConfig
import org.akce.android.boilerplate.cache.PreferencesHelper
import org.akce.android.boilerplate.cache.db.BufferoosDatabase
import org.akce.android.boilerplate.cache.mapper.BufferooEntityMapper
import org.akce.android.boilerplate.data.CityDataRepository
import org.akce.android.boilerplate.data.browse.interactor.GetCities
import org.akce.android.boilerplate.data.browse.interactor.GetSavedCities
import org.akce.android.boilerplate.data.browse.interactor.RemoveAll
import org.akce.android.boilerplate.data.browse.interactor.SaveCity
import org.akce.android.boilerplate.data.executor.JobExecutor
import org.akce.android.boilerplate.data.executor.PostExecutionThread
import org.akce.android.boilerplate.data.executor.ThreadExecutor
import org.akce.android.boilerplate.data.repository.CityRepository
import org.akce.android.boilerplate.data.source.WeatherDataStore
import org.akce.android.boilerplate.data.source.WeatherDataStoreFactory
import org.akce.android.boilerplate.remote.WeatherRemoteImpl
import org.akce.android.boilerplate.remote.WeatherServiceFactory
import org.akce.android.boilerplate.ui.UiThread
import org.akce.android.boilerplate.ui.browse.MainAdapter
import org.akce.android.boilerplate.ui.browse.MainViewModel
import org.akce.android.boilerplate.ui.weather.WeatherAdapter
import org.akce.android.boilerplate.ui.weather.WeatherViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module(override=true) {

    single { PreferencesHelper(androidContext()) }

    factory { org.akce.android.boilerplate.remote.mapper.WeatherEntityMapper() }

    single { JobExecutor() as ThreadExecutor }
    single { UiThread() as PostExecutionThread }

    single { Room.databaseBuilder(androidContext(),
            BufferoosDatabase::class.java, "bufferoos.db")
            .build() }
    factory { get<BufferoosDatabase>().cachedBufferooDao() }

    factory<WeatherDataStore>("remote") { WeatherRemoteImpl(get(), get()) }
    factory<WeatherDataStore>("local") { WeatherCacheImpl(get(), get(), get()) }
    factory { WeatherDataStoreFactory(get("local"), get("remote")) }

    factory { BufferooEntityMapper() }
    factory { WeatherServiceFactory.makeBuffeoorService(BuildConfig.DEBUG) }

    factory<CityRepository> { CityDataRepository(get()) }
}

val browseModule = module("Browse", override = true) {
    factory { MainAdapter() }
    factory { WeatherAdapter() }
    factory { GetCities(get(), get(), get()) }
    factory { SaveCity(get(), get(), get()) }
    factory { GetSavedCities(get(), get(), get()) }
    factory { RemoveAll(get(), get(), get()) }
    viewModel { MainViewModel(get(), get()) }
    viewModel { WeatherViewModel(get(), get(), get()) }
}