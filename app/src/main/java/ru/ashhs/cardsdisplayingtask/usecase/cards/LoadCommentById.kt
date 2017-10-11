package ru.ashhs.cardsdisplayingtask.usecase.cards

import io.reactivex.Scheduler
import io.reactivex.Single
import ru.ashhs.cardsdisplayingtask.network.RoutesServiceApi
import ru.ashhs.cardsdisplayingtask.network.dto.CommentDto
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by AleksanderSh on 09.10.2017.
 *
 * Interactor for loading Comment by id.
 */
@Singleton
class LoadCommentById
@Inject
constructor(@Named("WorkerThread") private val workerThread: Scheduler,
            @Named("UiThread") private val uiThread: Scheduler,
            private val routesServiceApi: RoutesServiceApi) {

    fun single(id: Long): Single<CommentDto> {
        return routesServiceApi.getCommentById(id)
                .subscribeOn(workerThread)
                .observeOn(uiThread)
    }
}