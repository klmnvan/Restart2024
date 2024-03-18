package com.example.chempionat2024.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Order(
    var id: Int = 0,
    @SerialName("profile_id")
    val profileId: String,
    @SerialName("origin_id")
    val originId: Int,
    @SerialName("package_id")
    val packageId: Int,
    @SerialName("tracking_number")
    val trackingNumber: String,
    val status: Int,
    val review: String,
    @SerialName("delivery_estimate")
    val deliveryEstimate: Int,
    @SerialName("is_active")
    val isActive: Boolean,
    @SerialName("created_at")
    val createdAt: String,
)
