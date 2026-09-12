pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MolinaXManager"

// Modul ditambahkan bertahap sesuai FASE IMPLEMENTASI di
// MolinaX-Manager-Blueprint-v1.md §11.
// Phase 0: belum ada modul aplikasi (app/, core-common/, feature-*).
