package org.akce.android.boilerplate.data.browse.interactor

import io.reactivex.Completable
import org.akce.android.boilerplate.data.executor.PostExecutionThread
import org.akce.android.boilerplate.data.executor.ThreadExecutor
import org.akce.android.boilerplate.data.interactor.CompletableUseCase
import org.akce.android.boilerplate.data.repository.CityRepository

open class RemoveAll(private val cityRepository: CityRepository,
                     threadExecutor: ThreadExecutor,
                     postExecutionThread: PostExecutionThread):
        CompletableUseCase<Unit>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: Unit): Completable {
        return cityRepository.clearBufferoos()
    }
}