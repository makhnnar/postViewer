package com.pedrogomez.postviewer.models.api.user

import kotlinx.serialization.Serializable

@Serializable
data class Geo(
    val lat: String,
    val lng: String
)