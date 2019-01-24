package org.akce.android.boilerplate.data.browse.interactor

import io.reactivex.Completable
import org.akce.android.boilerplate.data.browse.Request
import org.akce.android.boilerplate.data.executor.PostExecutionThread
import org.akce.android.boilerplate.data.executor.ThreadExecutor
import org.akce.android.boilerplate.data.interactor.CompletableUseCase
import org.akce.android.boilerplate.data.repository.CityRepository

open class SaveCity(private val cityRepository: CityRepository,
                    threadExecutor: ThreadExecutor,
                    postExecutionThread: PostExecutionThread):
        CompletableUseCase<String>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: String): Completable {
        return cityRepository.saveCity(Request("city", params))
    }
}