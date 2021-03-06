package ru.ashhs.cardsdisplayingtask.data.repository.routes

/**
 * Created by AleksanderSh on 02.11.2017.
 *
 * Interface for caching data from routers service.
 */
interface RoutesCache {

    operator fun <T : Any> get(clazz: Class<T>): T?

    operator fun <T : Any> set(clazz: Class<T>, value: T)
}