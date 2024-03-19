plugins {
    `java-library`
    alias(libs.plugins.jetbrainsKotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin{
    jvmToolchain(17)
}

dependencies{
    implementation(project(":common"))
    implementation(project(":domain"))
    implementation(project(":api:weather"))
    
    implementation(libs.coroutines)
    implementation(libs.koin.core)
    
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.serialization.json)
}