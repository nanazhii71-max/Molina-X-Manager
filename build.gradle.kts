// MolinaX Manager — root build config
// AGP 9.1.1 dipilih sebagai patch terbaru di jalur minor AGP 9.1 (dikunci di
// MolinaX-Manager-Blueprint-v1.md §2). Minimum Gradle untuk AGP 9.1.1 adalah
// 9.3.1 (terverifikasi di developer.android.com/build/releases/agp-9-1-0-release-notes,
// per September 2026) — terpenuhi oleh Gradle wrapper 9.3.1 yang sudah terpasang.
//
// AGP 9.0+ membawa built-in Kotlin support (dependensi runtime ke KGP 2.2.10
// secara otomatis) — plugin org.jetbrains.kotlin.android TIDAK diterapkan di
// proyek ini karena tidak kompatibel dengan DSL baru AGP 9.x.
//
// UI toolkit: Jetpack Compose. Compose Compiler tetap butuh plugin Gradle
// terpisah (org.jetbrains.kotlin.plugin.compose) meski Kotlin sudah built-in
// di AGP — versi dikunci 2.2.10, PERSIS sama dengan KGP bawaan AGP 9.1.1,
// supaya tidak perlu override versi KGP bawaan (terverifikasi di
// developer.android.com/develop/ui/compose/compiler, September 2026).
plugins {
    id("com.android.application") version "9.1.1" apply false
    id("com.android.library") version "9.1.1" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.10" apply false
}

// Dikonsumsi oleh setiap modul Android (app/, feature-*) mulai Phase 1.
extra["molinaxNdkVersion"] = "28.2.13676358"
extra["molinaxCompileSdk"] = 37
extra["molinaxMinSdk"] = 26
extra["molinaxTargetSdk"] = 26
extra["molinaxJvmToolchain"] = 21
extra["molinaxComposeBom"] = "2026.08.00"
