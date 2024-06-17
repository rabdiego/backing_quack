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
        maven("https://maven.sing-group.org/repository/maven/")
        maven("https://github.com/kshoji/JFugue-for-Android/raw/master/jfugue-android/snapshot")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://maven.sing-group.org/repository/maven/")
        maven("https://github.com/kshoji/JFugue-for-Android/raw/master/jfugue-android/snapshot")
    }
}

rootProject.name = "BackingQuack"
include(":app")
include(":core")
