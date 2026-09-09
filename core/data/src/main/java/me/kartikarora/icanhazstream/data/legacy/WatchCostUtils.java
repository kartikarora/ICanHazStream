package me.kartikarora.icanhazstream.data.legacy;

/**
 * Legacy Java utility class for streaming subscription and rental cost calculations.
 * This class contains static methods for regional pricing comparisons.
 */
// TODO: Step 04 — Convert this legacy Java class to Kotlin using Gemini
public class WatchCostUtils {

    /**
     * Calculate the cheapest option between renting, buying, and monthly subscription.
     *
     * @param rentPrice individual rental cost in AUD
     * @param buyPrice digital purchase cost in AUD
     * @param monthlySubPrice subscription cost per month in AUD
     * @param expectedRewatches estimated times user will watch the movie
     * @return recommended option description
     */
    public static String calculateBestValueOption(
            double rentPrice,
            double buyPrice,
            double monthlySubPrice,
            int expectedRewatches
    ) {
        if (expectedRewatches <= 1 && rentPrice > 0 && rentPrice < monthlySubPrice) {
            return "Rent (Cheapest single view)";
        }
        if (expectedRewatches > 3 && buyPrice > 0 && buyPrice < (monthlySubPrice * 2)) {
            return "Buy (Best for repeat watching)";
        }
        return "Stream Subscription";
    }

    /**
     * Calculate monthly streaming budget from active services.
     *
     * @param servicePrices array of active monthly subscription prices
     * @param annualDiscountApplied whether annual billing discount (15%) is applied
     * @return total monthly cost in AUD
     */
    public static double calculateMonthlySpend(double[] servicePrices, boolean annualDiscountApplied) {
        if (servicePrices == null || servicePrices.length == 0) {
            return 0.0;
        }
        double total = 0.0;
        for (double price : servicePrices) {
            total += price;
        }
        if (annualDiscountApplied) {
            total *= 0.85;
        }
        return Math.round(total * 100.0) / 100.0;
    }
}
