plugins {
    id("me.kartikarora.icanhazstream.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "me.kartikarora.icanhazstream.data"
}

dependencies {
    implementation(project(":core:model"))

    // JetBrains Ktor Client 3.5.2
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
}
