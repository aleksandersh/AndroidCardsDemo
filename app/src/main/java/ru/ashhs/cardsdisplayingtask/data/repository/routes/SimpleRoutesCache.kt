package ru.ashhs.cardsdisplayingtask.data.repository.routes

/**
 * Created by AleksanderSh on 02.11.2017.
 *
 * Simple crutch-cache.
 */
class SimpleRoutesCache : RoutesCache {

    private val cache: MutableMap<Class<*>, Any> = HashMap()

    /**
     * Getting cached value for given class.
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> get(clazz: Class<T>): T? {
        return cache[clazz] as? T
    }

    /**
     * Setting value for given class.
     */
    override fun <T : Any> set(clazz: Class<T>, value: T) {
        cache[clazz] = value
    }
}