package ru.ashhs.cardsdisplayingtask.domain.cards

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleSource
import ru.ashhs.cardsdisplayingtask.data.network.RoutesServiceApi
import ru.ashhs.cardsdisplayingtask.data.network.dto.UserDto
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

    private var cache: List<UserDto>? = null

    fun single(number: Int): Single<List<UserDto>> {
        if (cache != null) {
            return Single.just(cache)
        }

        val sources: List<SingleSource<UserDto>> = MutableList(number,
                { index -> routesServiceApi.getUserById(index.toLong() + 1) })

        return Single.merge(sources)
                .toList()
                .subscribeOn(workerThread)
                .observeOn(uiThread)
                .doOnSuccess { cache = it }
    }
}