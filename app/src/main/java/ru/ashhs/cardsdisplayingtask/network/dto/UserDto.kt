package ru.ashhs.cardsdisplayingtask.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by AleksanderSh on 09.10.2017.
 *
 * Data transfer object of User for jsonplaceholder service api.
 */
data class UserDto(
        @SerializedName("id") val id: Long?,
        @SerializedName("name") val name: String?,
        @SerializedName("username") val username: String?,
        @SerializedName("email") val email: String?,
        @SerializedName("address") val address: AddressDto?,
        @SerializedName("phone") val phone: String?,
        @SerializedName("website") val website: String?,
        @SerializedName("company") val company: CompanyDto?
)