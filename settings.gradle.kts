pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        id("com.android.library") version "7.4.0"
        id("com.android.application") version "7.4.0"
        id("org.jetbrains.kotlin.android") version "1.7.0"
        id("com.google.dagger.hilt.android") version "2.44.2"
        kotlin("plugin.serialization") version "1.7.20"
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "OdaInterviewCase"
include (":app")
