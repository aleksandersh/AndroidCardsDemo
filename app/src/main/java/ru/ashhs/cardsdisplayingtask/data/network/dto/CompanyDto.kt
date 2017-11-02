package ru.ashhs.cardsdisplayingtask.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by AleksanderSh on 09.10.2017.
 *
 * Data transfer object of Company for jsonplaceholder service api.
 */
data class CompanyDto(
        @SerializedName("name") val name: String?,
        @SerializedName("catchPhrase") val catchPhrase: String?,
        @SerializedName("bs") val bs: String?
)