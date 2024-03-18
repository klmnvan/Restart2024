package com.example.chempionat2024.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PackageDetail(
    val id: Int = 0,
    val weight: Float?,
    val worth: Float?,
    @SerialName("package_items")
    val packageItems: String?,
)
