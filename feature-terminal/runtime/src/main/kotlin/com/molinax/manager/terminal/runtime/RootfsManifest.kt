package com.molinax.manager.terminal.runtime

/**
 * Pin data untuk tarball rootfs Debian resmi (debuerreotype/docker-debian-artifacts).
 *
 * Desain: struct data murni, tidak menyentuh downloader/verifier/extractor,
 * sehingga suite baru (mis. trixie eksperimental) bisa ditambah tanpa membongkar
 * komponen lain — sesuai MolinaX-Manager-Blueprint-v1.md §4 & §8.1.
 *
 * PENTING: commit di sini me-refer ke commit repo `docker-debian-artifacts`
 * (mewakili snapshot build per-arsitektur, mencakup semua suite di dalamnya),
 * BUKAN commit debuerreotype tool itu sendiri. URL tarball dibentuk sebagai:
 *   https://github.com/debuerreotype/docker-debian-artifacts/raw/<commit>/<suite>/rootfs.tar.xz
 *
 * Sumber & verifikasi (fetch langsung, bukan asumsi):
 *   https://github.com/debuerreotype/debuerreotype.github.io/blob/master/index.md
 * Halaman ini eksplisit menyatakan mencerminkan build resmi TERBARU dari
 * Debian Docker official image. Build command upstream:
 *   ./examples/debian.sh --arch <dpkg-arch> out/ 'bookworm' '@1783900800'
 * (snapshot.debian.org timestamp 2026-07-13T00:00:00Z)
 *
 * Verifikasi ulang wajib dilakukan sebelum menaikkan versi rootfs di masa depan —
 * jangan pernah menaikkan versi hanya berdasar asumsi commit terbaru.
 */
data class RootfsManifest(
    val suite: String,
    val artifactCommit: String,
    val sha256: String,
    val url: String,
) {
    companion object {
        private const val ARTIFACTS_BASE =
            "https://github.com/debuerreotype/docker-debian-artifacts/raw"

        /** arm64-v8a — dpkg arch "arm64", bashbrew arch "arm64v8" */
        val BOOKWORM_ARM64_V8A = RootfsManifest(
            suite = "bookworm",
            artifactCommit = "fb7215b47dab72bdbdd59204a7b7914311431d90",
            sha256 = "202ecca447dbf1b3ac1b1e983d9363381ac6a34f8e22d7d786125d06754ebb76",
            url = "$ARTIFACTS_BASE/fb7215b47dab72bdbdd59204a7b7914311431d90/bookworm/rootfs.tar.xz",
        )

        /** armeabi-v7a — dpkg arch "armhf", bashbrew arch "arm32v7" */
        val BOOKWORM_ARMEABI_V7A = RootfsManifest(
            suite = "bookworm",
            artifactCommit = "e11403829ffe5cc336212fc63d1b80b93bb2375e",
            sha256 = "820f2706ab613f6b87af4970039f3115d957eaca9601f71f5cd152c04525d635",
            url = "$ARTIFACTS_BASE/e11403829ffe5cc336212fc63d1b80b93bb2375e/bookworm/rootfs.tar.xz",
        )

        /**
         * Pilih manifest sesuai ABI runtime device saat ini.
         * ABI di luar arm64-v8a/armeabi-v7a di luar scope (lihat blueprint §11 —
         * QEMU/cross-architecture emulation adalah fase lanjutan terpisah).
         */
        fun forAbi(abi: String): RootfsManifest = when (abi) {
            "arm64-v8a" -> BOOKWORM_ARM64_V8A
            "armeabi-v7a" -> BOOKWORM_ARMEABI_V7A
            else -> throw IllegalArgumentException(
                "ABI '$abi' tidak didukung. MolinaX Manager baseline hanya " +
                    "arm64-v8a dan armeabi-v7a (lihat MolinaX-Manager-Blueprint-v1.md §11)."
            )
        }
    }
}
