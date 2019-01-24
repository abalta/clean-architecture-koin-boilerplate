package org.akce.android.boilerplate.data.browse.interactor

import io.reactivex.Flowable
import org.akce.android.boilerplate.data.browse.Request
import org.akce.android.boilerplate.data.executor.PostExecutionThread
import org.akce.android.boilerplate.data.executor.ThreadExecutor
import org.akce.android.boilerplate.data.interactor.FlowableUseCase
import org.akce.android.boilerplate.data.repository.CityRepository

open class GetSavedCities(val cityRepository: CityRepository,
                          threadExecutor: ThreadExecutor,
                          postExecutionThread: PostExecutionThread):
        FlowableUseCase<List<Request>, Unit>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Unit?): Flowable<List<Request>> {
        return cityRepository.getCities()
    }
}