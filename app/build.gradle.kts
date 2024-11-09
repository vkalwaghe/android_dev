plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services") // Google Services plugin
}

android {
    namespace = "com.example.shirditaxi"
    compileSdk = 35 // Updated to 35

    defaultConfig {
        applicationId = "com.example.shirditaxi"
        minSdk = 21
        targetSdk = 35 // Updated to 35
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

    buildToolsVersion = "35.0.0"
}

dependencies {
    // Firebase BOM for managing Firebase dependencies' versions
    implementation(platform("com.google.firebase:firebase-bom:32.0.0"))

    // Firebase libraries with BOM versioning
    implementation("com.google.firebase:firebase-auth-ktx")         // Firebase Authentication
    implementation("com.google.firebase:firebase-database-ktx")     // Firebase Realtime Database
    implementation("com.google.firebase:firebase-firestore-ktx")    // Firebase Firestore (optional)

    // Google Sign-In library
    implementation("com.google.android.gms:play-services-auth:20.6.0")

    // AndroidX and Material Components
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Testing dependencies
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // JUnit dependency for unit tests
    testImplementation("junit:junit:4.13.2")
}
