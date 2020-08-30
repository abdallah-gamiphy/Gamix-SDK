package com.gamiphy.library.network.models.responses.bot

data class Language(
    val defaultLanguage: String,
    val supportedLanguages: List<String>
)