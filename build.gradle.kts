// MolinaX Manager — root build config
// AGP 9.1.1 dipilih sebagai patch terbaru di jalur minor AGP 9.1 (dikunci di
// MolinaX-Manager-Blueprint-v1.md §2). Minimum Gradle untuk AGP 9.1.1 adalah
// 9.3.1 (terverifikasi di developer.android.com/build/releases/agp-9-1-0-release-notes,
// per September 2026) — terpenuhi oleh Gradle wrapper 9.3.1 yang sudah terpasang.
//
// AGP 9.0+ membawa built-in Kotlin support (dependensi runtime ke KGP 2.2.10
// secara otomatis) — plugin org.jetbrains.kotlin.android TIDAK diterapkan di
// proyek ini karena tidak kompatibel dengan DSL baru AGP 9.x.
plugins {
    id("com.android.application") version "9.1.1" apply false
    id("com.android.library") version "9.1.1" apply false
}

// Dikonsumsi oleh setiap modul Android (app/, feature-*) mulai Phase 1, contoh:
//   android {
//       compileSdk = rootProject.extra["molinaxCompileSdk"] as Int
//       ndkVersion = rootProject.extra["molinaxNdkVersion"] as String
//       defaultConfig {
//           minSdk = rootProject.extra["molinaxMinSdk"] as Int
//           targetSdk = rootProject.extra["molinaxTargetSdk"] as Int
//       }
//       kotlin { jvmToolchain(rootProject.extra["molinaxJvmToolchain"] as Int) }
//   }
extra["molinaxNdkVersion"] = "28.2.13676358"
extra["molinaxCompileSdk"] = 37
extra["molinaxMinSdk"] = 26
extra["molinaxTargetSdk"] = 26
extra["molinaxJvmToolchain"] = 21
