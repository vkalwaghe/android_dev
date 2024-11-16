plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services") // Google Services plugin
}

android {
    namespace = "com.example.shirditaxi"
    compileSdk = 35 // Ensure your compileSdk matches the latest version you are targeting.

    defaultConfig {
        applicationId = "com.example.shirditaxi"
        minSdk = 21
        targetSdk = 35 // Ensure compatibility with the latest Android version.
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Firebase BOM (manages Firebase dependencies' versions)
    implementation(platform("com.google.firebase:firebase-bom:32.0.0"))

    // Firebase dependencies (version controlled by BOM)
    implementation("com.google.firebase:firebase-auth-ktx")       // Firebase Authentication
    implementation("com.google.firebase:firebase-database-ktx")   // Firebase Realtime Database
    implementation("com.google.firebase:firebase-firestore-ktx")  // Firestore (optional)

    // Google Sign-In library for Authentication
    implementation("com.google.android.gms:play-services-auth:20.6.0")

    // Google Location Services (needed for `FusedLocationProviderClient`)
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // OSM Map Library
    implementation("org.osmdroid:osmdroid-android:6.1.10")

    // AndroidX Libraries
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.preference:preference-ktx:1.1.1") // Preference Library

    // Material Design Components
    implementation("com.google.android.material:material:1.9.0")

    // Testing Dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
