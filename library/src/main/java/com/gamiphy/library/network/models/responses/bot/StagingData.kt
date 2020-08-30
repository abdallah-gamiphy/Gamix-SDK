package com.gamiphy.library.network.models.responses.bot

data class StagingData(
    val draftBot: String,
    val hasChanges: Boolean,
    val maps: Maps,
    val published: Boolean
)