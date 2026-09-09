package me.kartikarora.icanhazstream.model

import kotlinx.serialization.Serializable

@Serializable
enum class WatchOptionType {
    STREAM,
    RENT,
    BUY,
    FREE,
}

@Serializable
data class WatchOption(
    val provider: StreamingProvider,
    val region: String = "AU",
    val deepLinkUrl: String? = null,
)
