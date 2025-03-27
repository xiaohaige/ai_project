package com.empedocles.travelapp.domain.model

data class Image(
    val altText: String? = null,
    val height: Int? = null,
    val isHeroImage: Boolean? = null,
    val url: String,
    val width: Int? = null
)
