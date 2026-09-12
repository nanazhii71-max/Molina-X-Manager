plugins {
    id("com.android.library")
}

android {
    namespace = "com.molinax.manager.feature.terminal"
    compileSdk {
        version = release(rootProject.extra["molinaxCompileSdk"] as Int) {
            minorApiLevel = 0
        }
    }
    ndkVersion = rootProject.extra["molinaxNdkVersion"] as String

    defaultConfig {
        minSdk = rootProject.extra["molinaxMinSdk"] as Int
    }

    kotlin {
        jvmToolchain(rootProject.extra["molinaxJvmToolchain"] as Int)
    }
}
