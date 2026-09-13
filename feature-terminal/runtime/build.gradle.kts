// feature-terminal:runtime
// Bertanggung jawab atas siklus hidup rootfs Debian: manifest pin (suite,
// commit, SHA-256 per-ABI), fetch tarball resmi debuerreotype/docker-debian-artifacts,
// verifikasi checksum, ekstraksi ke ROOTFS, spawn TerminalSession via proot,
// dan RuntimeExecutionBridge (lihat MolinaX-Manager-Blueprint-v1.md §8.4).
//
// Berbeda dari terminal-emulator/terminal-view (Java murni + native JNI):
// modul ini berbasis Kotlin karena domainnya I/O + business logic (network,
// filesystem, proses), bukan wrapper native PTY.
plugins {
    id("com.android.library")
}

android {
    namespace = "com.molinax.manager.terminal.runtime"
    compileSdk {
        version = release(rootProject.extra["molinaxCompileSdk"] as Int) {
            minorApiLevel = 0
        }
    }

    defaultConfig {
        minSdk = rootProject.extra["molinaxMinSdk"] as Int
    }

    kotlin {
        jvmToolchain(rootProject.extra["molinaxJvmToolchain"] as Int)
    }
}

dependencies {
    // XZ for Java — dekompresi format .xz (rootfs.tar.xz Debian).
    // Pure Java, tanpa native code. Penulis asli format XZ (Lasse Collin).
    // Lisensi 0BSD (permissive, kompatibel searah ke GPLv3).
    // Verifikasi resmi: https://tukaani.org/xz/java.html (versi 1.12, rilis 2026-03-01)
    implementation("org.tukaani:xz:1.12")

    // Apache Commons Compress — parsing TAR (termasuk GNU/PAX long-name header
    // yang dipakai tarball Debian besar). Proyek resmi Apache, dipelihara aktif.
    // Lisensi Apache-2.0 (kompatibel searah ke GPLv3).
    // Verifikasi resmi: https://github.com/apache/commons-compress (versi 1.28.0, rilis 2026-05-27)
    //
    // CATATAN KEAMANAN WAJIB (lihat RuntimeExecutionBridge/TarExtractor saat ditulis):
    // commons-compress hanya PARSER — tidak menjamin ekstraksi aman secara default.
    // Referensi nyata: GHSA-9xq3-3fqg-4vg7 (proot-distro, path traversal via symlink
    // target tidak divalidasi). Validasi path/symlink WAJIB diimplementasikan sendiri
    // di layer ekstraksi kita, independen dari verifikasi checksum SHA-256.
    implementation("org.apache.commons:commons-compress:1.28.0")

    // JUnit4 — unit test JVM murni untuk verifikasi data RootfsManifest
    // (bentuk sha256, commit, url), bukan sekadar "berhasil dikompilasi".
    testImplementation("junit:junit:4.13.2")
}
