import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
import org.jetbrains.kotlin.gradle.internal.Kapt3GradleSubplugin.Companion.isKaptVerbose
import org.jetbrains.kotlin.gradle.plugin.kotlinToolingVersion

plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.realm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin{
    this.compilerOptions{
        jvmTarget.set(JVM_17)
    }
    jvmToolchain(17)
}

dependencies {
    implementation(project(":common"))
    
    implementation(libs.realm)
    implementation(libs.coroutines)
    implementation(libs.koin.core)
}