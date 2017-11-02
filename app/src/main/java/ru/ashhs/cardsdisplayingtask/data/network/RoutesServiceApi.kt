package ru.ashhs.cardsdisplayingtask.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.ashhs.cardsdisplayingtask.data.network.dto.*

/**
 * Created by AleksanderSh on 09.10.2017.
 *
 * jsonplaceholder service api.
 */
interface RoutesServiceApi {
    /**
     * Getting Post by id.
     */
    @GET("/posts/{id}")
    fun getPostById(@Path("id") id: Long): Single<PostDto>

    /**
     * Getting Comment by id.
     */
    @GET("comments/{id}")
    fun getCommentById(@Path("id") id: Long): Single<CommentDto>

    /**
     * Getting User by id.
     */
    @GET("users/{id}")
    fun getUserById(@Path("id") id: Long): Single<UserDto>

    /**
     * Getting Photo description by id.
     */
    @GET("photos/{id}")
    fun getPhotoDescriptionById(@Path("id") id: Long): Single<PhotoDescriptionDto>

    /**
     * Getting Task by id.
     */
    @GET("todos/{id}")
    fun getTaskById(@Path("id") id: Long): Single<TaskDto>
}