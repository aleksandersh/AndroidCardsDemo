package ru.ashhs.cardsdisplayingtask.domain.cards

import io.reactivex.Scheduler
import io.reactivex.Single
import ru.ashhs.cardsdisplayingtask.data.network.RoutesServiceApi
import ru.ashhs.cardsdisplayingtask.data.network.dto.PhotoDescriptionDto
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by AleksanderSh on 10.10.2017.
 *
 * Interactor for loading Photo description by id.
 */
@Singleton
class LoadPhotoDescriptionById
@Inject
constructor(@Named("WorkerThread") private val workerThread: Scheduler,
            @Named("UiThread") private val uiThread: Scheduler,
            private val routesServiceApi: RoutesServiceApi) {

    private var cache: PhotoDescriptionDto? = null

    fun single(id: Long): Single<PhotoDescriptionDto> {
        if (cache?.id == id) {
            return Single.just(cache)
        }

        return routesServiceApi.getPhotoDescriptionById(id)
                .subscribeOn(workerThread)
                .observeOn(uiThread)
                .doOnSuccess { cache = it }
    }
}