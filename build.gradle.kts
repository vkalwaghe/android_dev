// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.gms.google-services") version "4.3.15" apply false
}

// If you have dependencies that need to be added here, keep this block
dependencies {
    // Add other dependencies if necessary
}
