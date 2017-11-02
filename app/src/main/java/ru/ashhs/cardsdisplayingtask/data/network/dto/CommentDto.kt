package ru.ashhs.cardsdisplayingtask.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by AleksanderSh on 09.10.2017.
 *
 * Data transfer object of Comment for jsonplaceholder service api.
 */
data class CommentDto(
        @SerializedName("id") val id: Long?,
        @SerializedName("postId") val postId: Long?,
        @SerializedName("name") val title: String?,
        @SerializedName("email") val email: String?,
        @SerializedName("body") val text: String?
)