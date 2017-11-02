package ru.ashhs.cardsdisplayingtask.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by AleksanderSh on 09.10.2017.
 *
 * Data transfer object of Address for jsonplaceholder service api.
 */
data class AddressDto(
        @SerializedName("street") val street: String?,
        @SerializedName("suite") val suite: String?,
        @SerializedName("city") val city: String?,
        @SerializedName("zipcode") val zipcode: String?,
        @SerializedName("geo") val geo: GeoDto?
)