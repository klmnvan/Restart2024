package com.example.chempionat2024.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderDestination(
    @SerialName("order_id")
    val orderId: Int = 0,
    @SerialName("destination_id")
    val destinationId: Int = 0,
)
