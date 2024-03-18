package com.example.chempionat2024.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Order(
    var id: Int = 0,
    @SerialName("profile_id")
    val profileId: String = "",
    @SerialName("origin_id")
    val originId: Int = 0,
    @SerialName("package_id")
    val packageId: Int = 0,
    @SerialName("tracking_number")
    val trackingNumber: String = "",
    val status: Int = 0,
    val review: String = "",
    @SerialName("delivery_estimate")
    val deliveryEstimate: Int = 0,
    @SerialName("is_active")
    val isActive: Boolean = true,
    @SerialName("created_at")
    val createdAt: String = "",
)
