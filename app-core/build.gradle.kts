plugins {
    id("me.kartikarora.icanhazstream.library")
    id("me.kartikarora.icanhazstream.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "me.kartikarora.icanhazstream.core"
}

dependencies {
    implementation(project(":feature:explore"))
    implementation(project(":feature:detail"))
    implementation(project(":feature:watchlist"))
    implementation(project(":core:model"))
    implementation(project(":core:data"))
    implementation(project(":core:ui"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)

    // Navigation
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)

    implementation(libs.kotlinx.serialization.json)
}
