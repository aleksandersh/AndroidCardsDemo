package ru.ashhs.cardsdisplayingtask.usecase.cards

import io.reactivex.Scheduler
import io.reactivex.Single
import ru.ashhs.cardsdisplayingtask.network.RoutesServiceApi
import ru.ashhs.cardsdisplayingtask.network.dto.PhotoDescriptionDto
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

    fun single(id: Long): Single<PhotoDescriptionDto> {
        return routesServiceApi.getPhotoDescriptionById(id)
                .subscribeOn(workerThread)
                .observeOn(uiThread)
    }
}