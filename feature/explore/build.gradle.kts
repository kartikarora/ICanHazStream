plugins {
    id("me.kartikarora.icanhazstream.feature")
}

android {
    namespace = "me.kartikarora.icanhazstream.explore"
}

dependencies {
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.recyclerview)

    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
