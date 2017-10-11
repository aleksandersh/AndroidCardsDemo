package ru.ashhs.cardsdisplayingtask.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by AleksanderSh on 09.10.2017.
 *
 * Data transfer object of Post for jsonplaceholder service api.
 */
data class PostDto(
        @SerializedName("id") val id: Long?,
        @SerializedName("userId") val userId: Long?,
        @SerializedName("title") val title: String?,
        @SerializedName("body") val text: String?
)