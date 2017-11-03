package ru.ashhs.cardsdisplayingtask.domain.cards

import android.support.annotation.IntRange
import io.reactivex.Scheduler
import io.reactivex.Single
import ru.ashhs.cardsdisplayingtask.data.network.dto.PostDto
import ru.ashhs.cardsdisplayingtask.data.repository.routes.RoutesRepository
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by AleksanderSh on 09.10.2017.
 *
 * Interactor for loading Post by id.
 */
@Singleton
class LoadPostById
@Inject
constructor(@Named("WorkerThread") private val workerThread: Scheduler,
            @Named("UiThread") private val uiThread: Scheduler,
            private val routesRepository: RoutesRepository) {

    operator fun invoke(@IntRange(from = 1, to = 100) id: Long): Single<PostDto> {
        return routesRepository.getPostById(id)
                .subscribeOn(workerThread)
                .observeOn(uiThread)
    }
}