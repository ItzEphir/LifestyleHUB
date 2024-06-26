plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.serialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

dependencies{
    implementation(project(":common"))
    
    implementation(libs.coroutines)
    implementation(libs.serialization.json)
    implementation(libs.koin.core)
    implementation(libs.ktor.client.core)
}