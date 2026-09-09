plugins {
    id("me.kartikarora.icanhazstream.library")
    id("me.kartikarora.icanhazstream.compose")
}

android {
    namespace = "me.kartikarora.icanhazstream.ui"
}

dependencies {
    implementation(libs.androidx.compose.foundation)
}
