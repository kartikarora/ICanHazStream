plugins {
    id("me.kartikarora.icanhazstream.application")
}

android {
    namespace = "me.kartikarora.icanhazstream"
    defaultConfig {
        applicationId = "me.kartikarora.icanhazstream"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":app-core"))
}
