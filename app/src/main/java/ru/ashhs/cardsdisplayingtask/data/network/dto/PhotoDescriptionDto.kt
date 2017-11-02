package ru.ashhs.cardsdisplayingtask.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by AleksanderSh on 09.10.2017.
 *
 * Data transfer object of Photo description for jsonplaceholder service api.
 */
data class PhotoDescriptionDto(
        @SerializedName("id") val id: Long?,
        @SerializedName("albumId") val albumId: Long?,
        @SerializedName("title") val title: String?,
        @SerializedName("url") val url: String?,
        @SerializedName("thumbnailUrl") val thumbnailUrl: String?
)