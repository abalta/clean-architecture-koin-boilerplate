package org.buffer.android.boilerplate.data.browse.interactor

import io.reactivex.Flowable
import org.buffer.android.boilerplate.data.browse.WeatherResponse
import org.buffer.android.boilerplate.data.executor.PostExecutionThread
import org.buffer.android.boilerplate.data.executor.ThreadExecutor
import org.buffer.android.boilerplate.data.interactor.FlowableUseCase
import org.buffer.android.boilerplate.data.repository.CityRepository

open class GetCities(val cityRepository: CityRepository,
                     threadExecutor: ThreadExecutor,
                     postExecutionThread: PostExecutionThread):
        FlowableUseCase<WeatherResponse, String>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: String?): Flowable<WeatherResponse> {
        return cityRepository.findCity(params)
    }
}