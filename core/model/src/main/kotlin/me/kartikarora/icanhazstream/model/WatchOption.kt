package me.kartikarora.icanhazstream.model

import kotlinx.serialization.Serializable

@Serializable
enum class WatchOptionType {
    STREAM,   // Flatrate subscription (e.g. Netflix, Stan)
    RENT,     // Digital rental (e.g. Apple TV, Prime Video)
    BUY,      // Digital purchase
    FREE,     // Free with ads (e.g. ABC iview, SBS On Demand)
}

@Serializable
data class WatchOption(
    val provider: StreamingProvider,
    val region: String = "AU",
    val deepLinkUrl: String? = null,
)
