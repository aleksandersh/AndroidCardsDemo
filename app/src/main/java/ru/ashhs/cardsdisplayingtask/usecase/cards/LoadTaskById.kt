package ru.ashhs.cardsdisplayingtask.usecase.cards

import io.reactivex.Scheduler
import io.reactivex.Single
import ru.ashhs.cardsdisplayingtask.network.RoutesServiceApi
import ru.ashhs.cardsdisplayingtask.network.dto.TaskDto
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by AleksanderSh on 10.10.2017.
 *
 * Interactor for loading Post by id.
 */
@Singleton
class LoadTaskById
@Inject
constructor(@Named("WorkerThread") private val workerThread: Scheduler,
            @Named("UiThread") private val uiThread: Scheduler,
            private val routesServiceApi: RoutesServiceApi) {

    private var cache: TaskDto? = null

    fun single(id: Long): Single<TaskDto> {
        if (cache?.id == id) {
            return Single.just(cache)
        }

        return routesServiceApi.getTaskById(id)
                .subscribeOn(workerThread)
                .observeOn(uiThread)
                .doOnSuccess { cache = it }
    }
}