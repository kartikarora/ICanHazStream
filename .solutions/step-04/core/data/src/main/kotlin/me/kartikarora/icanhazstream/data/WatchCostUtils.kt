package me.kartikarora.icanhazstream.data

import kotlin.math.round

/**
 * Utility functions for streaming subscription and rental cost calculations.
 */
object WatchCostUtils {

    /**
     * Calculate the cheapest option between renting, buying, and monthly subscription.
     */
    fun calculateBestValueOption(
        rentPrice: Double,
        buyPrice: Double,
        monthlySubPrice: Double,
        expectedRewatches: Int,
    ): String {
        if (expectedRewatches <= 1 && rentPrice > 0 && rentPrice < monthlySubPrice) {
            return "Rent (Cheapest single view)"
        }
        if (expectedRewatches > 3 && buyPrice > 0 && buyPrice < (monthlySubPrice * 2)) {
            return "Buy (Best for repeat watching)"
        }
        return "Stream Subscription"
    }

    /**
     * Calculate monthly streaming spend from active services.
     */
    fun calculateMonthlySpend(
        servicePrices: List<Double>,
        annualDiscountApplied: Boolean = false,
    ): Double {
        if (servicePrices.isEmpty()) return 0.0
        var total = servicePrices.sum()
        if (annualDiscountApplied) {
            total *= 0.85
        }
        return round(total * 100.0) / 100.0
    }
}
