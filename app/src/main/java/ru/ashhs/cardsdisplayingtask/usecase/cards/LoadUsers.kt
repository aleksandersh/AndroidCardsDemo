package ru.ashhs.cardsdisplayingtask.usecase.cards

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleSource
import ru.ashhs.cardsdisplayingtask.network.RoutesServiceApi
import ru.ashhs.cardsdisplayingtask.network.dto.UserDto
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by AleksanderSh on 09.10.2017.
 *
 * Interactor for loading requested number of Users.
 */
@Singleton
class LoadUsers
@Inject
constructor(@Named("WorkerThread") private val workerThread: Scheduler,
            @Named("UiThread") private val uiThread: Scheduler,
            private val routesServiceApi: RoutesServiceApi) {

    fun single(number: Int): Single<List<UserDto>> {
        val sources: List<SingleSource<UserDto>> = MutableList(number,
                { index -> routesServiceApi.getUserById(index.toLong() + 1) })

        return Single.merge(sources)
                .toList()
                .subscribeOn(workerThread)
                .observeOn(uiThread)
    }
}