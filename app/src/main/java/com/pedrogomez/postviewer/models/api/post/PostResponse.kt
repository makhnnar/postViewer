package com.pedrogomez.postviewer.models.api.post

import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)