//plugins {
//    kotlin("multiplatform")
//    id("com.android.library")
//    kotlin("plugin.serialization")
//    id("org.jetbrains.compose")
//    id("dev.icerock.mobile.multiplatform-resources")
//    id("de.jensklingenberg.ktorfit") version "1.0.0"
//    id("com.google.devtools.ksp") version "1.8.20-1.0.11"
//}
//
//configure<de.jensklingenberg.ktorfit.gradle.KtorfitGradleConfiguration> {
//    version = libs.versions.ktorfit.get()
//}
//
//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//    kotlinOptions.jvmTarget = "1.8"
//}
//
//java {
//    toolchain {
//        languageVersion.set(JavaLanguageVersion.of(17))
//    }
//}
//
//kotlin {
//    android()
//    jvm("desktop")
//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach {
//        it.binaries.framework {
//            baseName = "transaction"
//        }
//    }
//
//    sourceSets {
//        val commonMain by getting {
//            dependencies {
//                implementation(project(":shared"))
//                //Compose
//                implementation(compose.ui)
//                implementation(compose.foundation)
//                implementation(compose.material)
//                implementation(compose.runtime)
//                implementation(libs.compose.resource)
//                //Network
//                implementation(libs.ktorfit)
//                implementation(libs.ktor.core)
//                //Coroutines
//                implementation(libs.kotlinx.coroutines.core)
//                //JSON
//                implementation(libs.kotlinx.serialization.json)
//                // DI
//                api(libs.koin.core)
//                //Navigation
//                implementation(libs.voyager.navigator)
//                //Logging
//                implementation(libs.napier)
//            }
//        }
//        val commonTest by getting {
//            dependencies {
//                implementation(kotlin("test"))
//            }
//        }
//        val androidMain by getting
//        val androidUnitTest by getting
//        val iosX64Main by getting
//        val iosArm64Main by getting
//        val iosSimulatorArm64Main by getting
//        val iosMain by creating {
//            dependsOn(commonMain)
//            iosX64Main.dependsOn(this)
//            iosArm64Main.dependsOn(this)
//            iosSimulatorArm64Main.dependsOn(this)
//            dependencies {
//            }
//        }
//        val iosX64Test by getting
//        val iosArm64Test by getting
//        val iosSimulatorArm64Test by getting
//        val iosTest by creating {
//            dependsOn(commonTest)
//            iosX64Test.dependsOn(this)
//            iosArm64Test.dependsOn(this)
//            iosSimulatorArm64Test.dependsOn(this)
//        }
//        val desktopMain by getting {
//            kotlin.srcDirs("src/jvmMain/kotlin")
//            dependsOn(commonMain)
//            dependencies {
//
//            }
//        }
//    }
//}
//
//android {
//    namespace = "com.aibles.transaction"
//    compileSdk = (findProperty("android.compileSdk") as String).toInt()
//    defaultConfig {
//        minSdk = (findProperty("android.minSdk") as String).toInt()
//    }
//}
//
//dependencies {
//    add("kspCommonMainMetadata", libs.ktorfit.ksp)
//    add("kspAndroid", libs.ktorfit.ksp)
//    add("kspIosX64", libs.ktorfit.ksp)
//    add("kspIosSimulatorArm64", libs.ktorfit.ksp)
//    add("kspDesktop", libs.ktorfit.ksp)
//    add("kspIosArm64", libs.ktorfit.ksp)
//}

//New

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
        id("org.jetbrains.compose")
    id("com.google.devtools.ksp") version "1.8.20-1.0.11"
    id("de.jensklingenberg.ktorfit") version "1.0.0"
}

val ktorfitVersion = "1.4.0"

configure<de.jensklingenberg.ktorfit.gradle.KtorfitGradleConfiguration> {
    version = ktorfitVersion
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

kotlin {
    android()

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "transaction"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)
                implementation(libs.ktorfit)
                implementation(libs.kotlinx.coroutines.core)
//                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.napier)
                implementation(libs.koin.core)
                implementation(libs.voyager.navigator)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            kotlin.srcDirs("src/jvmMain/kotlin")
            dependencies {
                implementation(libs.ktor.client.cio)
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.ktor.client.ios)
            }
        }

//        val jvmMain by getting {
//            dependencies {
//            }
//        }

        val desktopMain by getting {
            kotlin.srcDirs("src/jvmMain/kotlin")
            dependencies {
                implementation(libs.ktor.client.cio)
            }
        }
    }
}

android {
    namespace = "com.aibles.transaction"
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    add("kspCommonMainMetadata", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
    add("kspDesktop", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
    add("kspAndroid", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
    add("kspIosX64", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
    add("kspIosArm64", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
//    add("kspJs", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
    add("kspIosSimulatorArm64", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
}