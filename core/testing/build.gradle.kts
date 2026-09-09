plugins {
    id("me.kartikarora.icanhazstream.library")
}

android {
    namespace = "me.kartikarora.icanhazstream.testing"
}

dependencies {
    api(project(":core:model"))
    api(project(":core:data"))

    api(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.coroutines.test)
    api(libs.junit5.api)
    api(libs.turbine)
    api(libs.androidx.arch.core.testing)
}
