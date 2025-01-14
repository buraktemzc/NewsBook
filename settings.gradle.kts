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

rootProject.name = "NewsBook"
include(":app")
include(":core:model")
include(":core:theme")
include(":core:ui")
include(":base:data")
include(":network:model")
include(":network:manager")
include(":base:domain")
include(":base:ui")
include(":data")
include(":domain")
include(":features:home-api")
include(":features:home-impl")
