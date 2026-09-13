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

    // proot dibundel sebagai "libproot.so" via feature-terminal/src/main/jniLibs/<abi>/
    // agar diekstrak jadi file fisik executable di nativeLibraryDir saat instalasi
    // (bukan cuma di-mmap dari dalam APK) — proot dieksekusi sebagai subprocess nyata,
    // bukan dimuat sebagai shared library lewat dlopen/System.loadLibrary.
    // Referensi resmi: developer.android.com/reference/tools/gradle-api .../dsl/JniLibsPackaging
    packaging {
        jniLibs {
            useLegacyPackaging = true
        }
    }

    kotlin {
        jvmToolchain(rootProject.extra["molinaxJvmToolchain"] as Int)
    }
}

dependencies {
    // Compose BOM — versi tunggal dikunci di root (molinaxComposeBom = 2026.08.00),
    // cocok dengan compileSdk 37 (verifikasi: Jetpack Compose August '26 release notes)
    implementation(platform("androidx.compose:compose-bom:${rootProject.extra["molinaxComposeBom"]}"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Activity + Compose entry point (stable — developer.android.com/jetpack/androidx/releases/activity)
    implementation("androidx.activity:activity-compose:1.13.0")

    // Navigation 3 (stable — developer.android.com/jetpack/androidx/releases/navigation3)
    implementation("androidx.navigation3:navigation3-runtime:1.1.7")
    implementation("androidx.navigation3:navigation3-ui:1.1.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-navigation3:2.10.0")
}
