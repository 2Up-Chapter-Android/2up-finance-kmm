import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm {}
    sourceSets {
        val jvmMain by getting  {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(project(":shared"))
                implementation(project(":feature:authentication"))
                implementation(project(":feature:transaction"))
                implementation(libs.koin.core)
                //Navigation
                implementation(libs.voyager.navigator)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.aibles.finance2upkmm.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)

            packageVersion = "1.0.0"

            windows {
                menu = true
                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html

            }

            macOS {
                // Use -Pcompose.desktop.mac.sign=true to sign and notarize.

            }
        }
    }
}
