package org.akce.android.boilerplate.data.browse.interactor

import io.reactivex.Flowable
import org.akce.android.boilerplate.data.browse.WeatherResponse
import org.akce.android.boilerplate.data.executor.PostExecutionThread
import org.akce.android.boilerplate.data.executor.ThreadExecutor
import org.akce.android.boilerplate.data.interactor.FlowableUseCase
import org.akce.android.boilerplate.data.repository.CityRepository

open class GetCities(val cityRepository: CityRepository,
                     threadExecutor: ThreadExecutor,
                     postExecutionThread: PostExecutionThread):
        FlowableUseCase<WeatherResponse, String>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: String?): Flowable<WeatherResponse> {
        return cityRepository.findCity(params)
    }
}