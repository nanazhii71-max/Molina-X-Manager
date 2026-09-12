// feature-terminal:terminal-view
// Vendor-in-source dari termux/termux-app v0.118.3 (Apache License 2.0),
// lihat VENDORED_FROM.md. Package direname com.termux.view -> com.molinax.manager.terminal.view.
//
// Lapisan rendering/touch (TerminalView, TerminalRenderer) — terpisah dari
// terminal-emulator (logic murni) mengikuti struktur asli upstream.
plugins {
    id("com.android.library")
}

android {
    namespace = "com.molinax.manager.terminal.view"
    compileSdk {
        version = release(rootProject.extra["molinaxCompileSdk"] as Int) {
            minorApiLevel = 0
        }
    }
    ndkVersion = rootProject.extra["molinaxNdkVersion"] as String

    defaultConfig {
        minSdk = rootProject.extra["molinaxMinSdk"] as Int
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":feature-terminal:terminal-emulator"))
    // Dipakai TerminalView.java: @Nullable, @RequiresApi (stable, verifikasi
    // developer.android.com/jetpack/androidx/releases/annotation, April 2026)
    implementation("androidx.annotation:annotation:1.10.0")
}
