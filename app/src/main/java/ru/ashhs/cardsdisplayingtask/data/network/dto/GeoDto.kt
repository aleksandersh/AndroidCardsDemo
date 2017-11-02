package ru.ashhs.cardsdisplayingtask.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by AleksanderSh on 09.10.2017.
 *
 * Data transfer object of Geo-coordinates for jsonplaceholder service api.
 */
data class GeoDto(
        @SerializedName("lat") val latitude: String?,
        @SerializedName("lng") val longitude: String?
)