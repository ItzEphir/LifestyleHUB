plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.realm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin{
    jvmToolchain(17)
}

dependencies {
    implementation(project(":common"))
    
    implementation(libs.realm)
    implementation(libs.coroutines)
    implementation(libs.koin.core)
}