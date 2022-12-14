pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Pokemons"
include(":app")
include(":util")
include(":core-data")
include(":core-database")
include(":core-remote")
include(":core-model")
include(":core-datastore")
