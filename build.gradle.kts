import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.include

plugins {
    id("com.android.application") version "8.1.0" apply false
    id("com.android.library") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") apply false
    id("com.google.gms.google-services") version "4.3.15" apply false
}

dependencies {
    val repositoriesMode = null
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS) // Use settings repositories if defined
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Shirdi_Taxi"
include(":app")
