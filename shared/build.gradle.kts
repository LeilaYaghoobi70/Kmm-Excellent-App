import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kspTool)
    kotlin("plugin.serialization") version "2.0.0"
}

kotlin {
    jvmToolchain(17)
    targetHierarchy.default()

    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            dependencies {
                with(libs.koin) {
                    api(core)
                    api(test)
                    api(annotations)
                }
                with(libs.ktor) {
                    implementation(client.core)
                    implementation(client.content.negotiation)
                    implementation(serialization.kotlinx.json)
                    implementation(client.logging)
                }
                implementation(libs.kotlinx.coroutines.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.android)
                implementation(libs.ktor.client.android.cio)
                implementation(libs.androidx.lifecycle.viewmodel.ktx)
                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(libs.androidx.lifecycle.viewmodel.compose)
                // koin
                implementation(libs.koin.android)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
                implementation(libs.ktor.client.ios.cio)
            }
        }
    }
}
task("testClasses")
android {
    namespace = "app.google.excellentapp"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
dependencies {
    add("kspCommonMainMetadata", libs.koin.kspCompiler)
}

tasks.withType<KotlinCompile<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}
afterEvaluate {
    tasks.filter {
        it.name.contains("SourcesJar", true)
    }?.forEach {
        println("SourceJarTask====>${it.name}")
        it.dependsOn("kspCommonMainKotlinMetadata")
    }
}
