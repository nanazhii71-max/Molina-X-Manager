// feature-terminal:terminal-emulator
// Vendor-in-source dari termux/termux-app v0.118.3 (Apache License 2.0),
// lihat VENDORED_FROM.md. Package direname com.termux.terminal -> com.molinax.manager.terminal.
//
// Module Java murni (tanpa Android View) + native libtermux.so (PTY subprocess
// via fork/exec) yang dipanggil JNI.java. proot dijalankan SEBAGAI subprocess
// di dalam PTY yang dibuat native lib ini — proot dan libtermux adalah dua
// komponen native berbeda yang saling melengkapi, bukan salah satu menggantikan
// yang lain.
plugins {
    id("com.android.library")
}

android {
    namespace = "com.molinax.manager.terminal"
    compileSdk {
        version = release(rootProject.extra["molinaxCompileSdk"] as Int) {
            minorApiLevel = 0
        }
    }
    ndkVersion = rootProject.extra["molinaxNdkVersion"] as String

    defaultConfig {
        minSdk = rootProject.extra["molinaxMinSdk"] as Int

        externalNativeBuild {
            ndkBuild {
                cFlags += listOf(
                    "-std=c11", "-Wall", "-Wextra", "-Werror", "-Os",
                    "-fno-stack-protector", "-Wl,--gc-sections"
                )
            }
        }

        ndk {
            // Fokus ABI Android 8 baseline (selaras native/proot, native/mpv)
            abiFilters += listOf("arm64-v8a", "armeabi-v7a")
        }
    }

    externalNativeBuild {
        ndkBuild {
            path = file("src/main/jni/Android.mk")
        }
    }

    // Java murni (tanpa Kotlin) — mengikuti bytecode target modern, terpisah
    // dari toolchain Kotlin (molinaxJvmToolchain) karena tidak ada source Kotlin.
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
