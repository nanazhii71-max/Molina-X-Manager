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

include(
    ":app",
    ":core-common",
    ":feature-player",
    ":feature-editor",
    ":feature-terminal",
    ":feature-terminal:terminal-emulator",
    ":feature-terminal:terminal-view",
    ":feature-terminal:runtime",
    ":feature-utilities"
)
