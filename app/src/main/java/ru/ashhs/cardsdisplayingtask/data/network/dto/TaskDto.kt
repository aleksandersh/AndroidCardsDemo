package ru.ashhs.cardsdisplayingtask.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by AleksanderSh on 09.10.2017.
 *
 * Data transfer object of Task for jsonplaceholder service api.
 */
data class TaskDto(
        @SerializedName("id") val id: Long?,
        @SerializedName("userId") val userId: Long?,
        @SerializedName("title") val title: String?,
        @SerializedName("completed") val completed: Boolean?
)