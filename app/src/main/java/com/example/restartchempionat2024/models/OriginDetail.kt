package com.example.chempionat2024.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OriginDetail(
    val id: Int = 0,
    val address: String = "",
    @SerialName("state_country")
    val stateCountry: String = "",
    val phone: String = "",
    val others: String? = "",
)
