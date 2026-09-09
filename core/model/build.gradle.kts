plugins {
    id("me.kartikarora.icanhazstream.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "me.kartikarora.icanhazstream.model"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
