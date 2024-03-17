package com.example.restartchempionat2024.models

import kotlinx.serialization.SerialName

/** data class, который содержит все поля, которые можно получить из соответствующей таблицы *** в supabse */
data class Profile (
    var id: String? = "",
    @SerialName("updated_at")
    var updatedAt: String? = "",
    var name: String? = "",
    var phone: String? = "",
    @SerialName("profile_image")
    var profileImageUrl: String? = null,
    var email: String? = "",
    @SerialName("current_balance")
    var currentBalance: Int? = 0,
)
