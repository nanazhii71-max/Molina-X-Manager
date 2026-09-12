plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.molinax.manager"
    compileSdk {
        version = release(rootProject.extra["molinaxCompileSdk"] as Int) {
            minorApiLevel = 0
        }
    }
    ndkVersion = rootProject.extra["molinaxNdkVersion"] as String

    defaultConfig {
        applicationId = "com.molinax.manager"
        minSdk = rootProject.extra["molinaxMinSdk"] as Int
        targetSdk = rootProject.extra["molinaxTargetSdk"] as Int
        versionCode = 1
        versionName = "0.1.0"
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    kotlin {
        jvmToolchain(rootProject.extra["molinaxJvmToolchain"] as Int)
    }
}

dependencies {
    // Dependency Compose (BOM + activity-compose + navigation-compose) akan
    // ditambahkan pada langkah berikutnya (penulisan App Host: MainActivity,
    // NavigationHost, Bottom Navigation) setelah versi androidx.activity dan
    // androidx.navigation terkini diverifikasi resmi.
}
