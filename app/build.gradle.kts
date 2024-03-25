plugins {
    alias(libs.plugins.application)
    alias(libs.plugins.android)
}

android {
    namespace = "com.ephirium.lifestylehub"
    compileSdk = 34
    
    defaultConfig {
        applicationId = "com.ephirium.lifestylehub"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    
    buildTypes {
        all{
            @Suppress("SpellCheckingInspection", "RedundantSuppression") buildConfigField("String", "WEATHER_API_KEY", "\"dbaad18638759894a816af331cfb5672\"")
            @Suppress("SpellCheckingInspection") buildConfigField("String", "PLACES_API_KEY", "\"fsq3I0lJ8GNhhnjGhdkHT/CGqrMe5o+cEDLPms2+o6fSG90=\"")
            @Suppress("SpellCheckingInspection") buildConfigField("String", "RANDOM_USER_API_KEY", "\"PUZ4-1ZWF-Y25Y-RTYN\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    
    implementation(project(":androidBase"))
    implementation(project(":feature:currentWeather"))
    implementation(project(":feature:recommendations"))
    implementation(project(":feature:placeDetails"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:leisure"))
    implementation(project(":data"))
    implementation(project(":domain"))
    
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(libs.androidx.navigation.compose)
}