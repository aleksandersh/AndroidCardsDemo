package ru.ashhs.cardsdisplayingtask.data.repository.routes

import io.reactivex.Single
import ru.ashhs.cardsdisplayingtask.data.network.RoutesServiceApi
import ru.ashhs.cardsdisplayingtask.data.network.dto.*

/**
 * Created by AleksanderSh on 02.11.2017.
 *
 * Routes service repository.
 */
class RoutesRepository constructor(private val serviceApi: RoutesServiceApi,
                                   private val routesCache: RoutesCache) {

    fun getCommentById(id: Long): Single<CommentDto> {
        return getWithCache({ it.id == id }, { serviceApi.getCommentById(id) })
    }

    fun getPhotoDescriptionById(id: Long): Single<PhotoDescriptionDto> {
        return getWithCache({ it.id == id }, { serviceApi.getPhotoDescriptionById(id) })
    }

    fun getPostById(id: Long): Single<PostDto> {
        return getWithCache({ it.id == id }, { serviceApi.getPostById(id) })
    }

    fun getTaskById(id: Long): Single<TaskDto> {
        return getWithCache({ it.id == id }, { serviceApi.getTaskById(id) })
    }

    fun getUserById(id: Long): Single<UserDto> {
        return getWithCache({ it.id == id }, { serviceApi.getUserById(id) })
    }

    /**
     * Kotlin facerolling!
     *
     * @param filter Function that validates cached value.
     * @param method Function that returns network-source.
     */
    private inline fun <reified T : Any> getWithCache(filter: (T) -> Boolean, method: () -> Single<T>): Single<T> {
        val clazz = T::class.java

        routesCache[clazz]
                ?.takeIf(filter)
                ?.let { return Single.just(it) }

        return method()
                .doOnSuccess { routesCache[clazz] = it }
    }
}