pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        val composeVersion = extra["compose.version"] as String
        id("org.jetbrains.compose").version(composeVersion)
        id("org.jetbrains.kotlin.jvm") version "1.8.0"
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Finance2upKMM"
include(":androidApp")
include(":shared")
include(":feature")
include(":feature:authentication")
include(":desktopApp")
