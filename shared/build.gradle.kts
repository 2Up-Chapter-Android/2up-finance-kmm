plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("de.jensklingenberg.ktorfit") version "1.0.0"
    id("com.google.devtools.ksp") version "1.8.20-1.0.11"
    id("org.jetbrains.compose")
    id("com.squareup.sqldelight")
}

version = "1.0-SNAPSHOT"

configure<de.jensklingenberg.ktorfit.gradle.KtorfitGradleConfiguration> {
    version = libs.versions.ktorfit.get()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

kotlin {
    jvm("desktop")
    android()


    ios()
    iosSimulatorArm64()

    cocoapods {
        summary = "Shared code for the sample"
        homepage = "https://github.com/JetBrains/compose-jb"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = false
//            export(libs.moko.resources)
//            export(project(":common:resources"))
//            export(project(":libraries:scanqr"))
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
        extraSpecAttributes["exclude_files"] = "['src/commonMain/resources/MR/**']"
    }

    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        binaries.withType<org.jetbrains.kotlin.gradle.plugin.mpp.Framework> {
            linkerOpts.add("-lsqlite3")
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.napier)
                //Network
                implementation(libs.ktorfit)
                implementation(libs.ktor.core)
                implementation(libs.ktor.logging)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                //Coroutines
                implementation(libs.kotlinx.coroutines.core)
                //JSON
                implementation(libs.kotlinx.serialization.json)
                // DI
                api(libs.koin.core)
                // database
                implementation(libs.sqldelight.runtime)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation(compose.ui)
                implementation(compose.runtime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.hawk)
                api(libs.koin.core)
                implementation(libs.napier)
                //Network
                implementation(libs.ktor.client.okhttp)
                implementation(libs.sqldelight.driver.android)
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {//Network
                implementation(libs.ktor.client.ios)
                api(libs.koin.core)
                implementation(libs.sqldelight.driver.ios)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
//        val iosTest by creating {
//            dependsOn(commonTest)
//            iosX64Test.dependsOn(this)
//            iosArm64Test.dependsOn(this)
//            iosSimulatorArm64Test.dependsOn(this)
//        }

        val desktopMain by getting {
            kotlin.srcDirs("src/jvmMain/kotlin")
            dependsOn(commonMain)
            dependencies {
                implementation(libs.sqldelight.driver.desktop)
                implementation(libs.credentialSecureStorage)
            }
        }
    }
}

sqldelight {
    database("Finance2UpKMMDatabase") {
        packageName = "com.aibles.finance2upkmm.database"
        sourceFolders = listOf("sqldelight")
    }
}

android {
    namespace = "com.aibles.shared"
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.ktorfit.ksp)
    add("kspAndroid", libs.ktorfit.ksp)
    add("kspIosX64", libs.ktorfit.ksp)
    add("kspIosSimulatorArm64", libs.ktorfit.ksp)
    add("kspDesktop", libs.ktorfit.ksp)
    add("kspIosArm64", libs.ktorfit.ksp)
}