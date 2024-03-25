// for dependencyResolutionManagement
@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "LifestyleHUB"
include(":androidBase")
include(":common")
include(":app")
include(":feature:currentWeather")
include(":feature:recommendations")
include(":feature:placeDetails")
include(":feature:auth")
include(":feature:leisure")
include(":domain")
include(":data")
include(":database:placeInfo")
include(":api:placeDetails")
include(":api:weather")
include(":api:places")
