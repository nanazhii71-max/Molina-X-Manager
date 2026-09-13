package com.molinax.manager.terminal.runtime

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Verifikasi data pin RootfsManifest — bukan sekadar test "berhasil compile",
 * tapi memvalidasi bentuk nyata data yang akan dipakai untuk download+verify
 * rootfs Debian: panjang SHA-256 (64 hex char), commit, dan URL yang terbentuk.
 */
class RootfsManifestTest {

    private val sha256Pattern = Regex("^[0-9a-f]{64}$")

    @Test
    fun `arm64-v8a manifest sesuai pin resmi debuerreotype`() {
        val manifest = RootfsManifest.forAbi("arm64-v8a")
        assertEquals("bookworm", manifest.suite)
        assertEquals("fb7215b47dab72bdbdd59204a7b7914311431d90", manifest.artifactCommit)
        assertTrue(
            "SHA-256 arm64-v8a harus 64 karakter hex lowercase, aktual: ${manifest.sha256}",
            sha256Pattern.matches(manifest.sha256)
        )
        assertEquals(
            "https://github.com/debuerreotype/docker-debian-artifacts/raw/" +
                "fb7215b47dab72bdbdd59204a7b7914311431d90/bookworm/rootfs.tar.xz",
            manifest.url
        )
    }

    @Test
    fun `armeabi-v7a manifest sesuai pin resmi debuerreotype`() {
        val manifest = RootfsManifest.forAbi("armeabi-v7a")
        assertEquals("bookworm", manifest.suite)
        assertEquals("e11403829ffe5cc336212fc63d1b80b93bb2375e", manifest.artifactCommit)
        assertTrue(
            "SHA-256 armeabi-v7a harus 64 karakter hex lowercase, aktual: ${manifest.sha256}",
            sha256Pattern.matches(manifest.sha256)
        )
        assertEquals(
            "https://github.com/debuerreotype/docker-debian-artifacts/raw/" +
                "e11403829ffe5cc336212fc63d1b80b93bb2375e/bookworm/rootfs.tar.xz",
            manifest.url
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `ABI tidak didukung wajib melempar IllegalArgumentException`() {
        RootfsManifest.forAbi("x86_64")
    }

    @Test
    fun `kedua manifest ABI tidak boleh berbagi commit atau sha256 yang sama`() {
        val arm64 = RootfsManifest.forAbi("arm64-v8a")
        val armv7 = RootfsManifest.forAbi("armeabi-v7a")
        assertTrue(arm64.artifactCommit != armv7.artifactCommit)
        assertTrue(arm64.sha256 != armv7.sha256)
    }
}
