package com.example.restartchempionat2024.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DestinationDetail(
    var id: Int = 0,
    var address: String,
    @SerialName("state_country")
    var stateCountry: String,
    var phone: String,
    var others: String?,
)
