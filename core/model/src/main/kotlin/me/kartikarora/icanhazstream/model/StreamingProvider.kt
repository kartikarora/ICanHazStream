package me.kartikarora.icanhazstream.model

import kotlinx.serialization.Serializable

@Serializable
data class StreamingProvider(
    val id: String,
    val name: String,
    val logoPath: String? = null,
    val type: WatchOptionType,
    val price: Double? = null,
    val quality: String? = null, // 4K, HD, SD
)
