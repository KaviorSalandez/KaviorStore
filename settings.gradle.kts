pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        //truy cap maven
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        //dung cho slider
        jcenter()

    }
}

rootProject.name = "PRM392Project"
include(":app")
 