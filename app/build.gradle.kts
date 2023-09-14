plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.roomdb_kotlin"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.roomdb_kotlin"
        minSdk = 28
        targetSdk = 33
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
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    configurations { // Adding for resolving Duplicate classes compile-time exception
        api.configure { exclude(group = "org.jetbrains", module = "annotations") }
        implementation.configure { exclude(group = "org.jetbrains", module = "annotations") }
    }
    packagingOptions {
        resources {
            excludes += "META-INF/*"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    // Compose Navigation
    implementation(libs.androidx.navigation.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation(libs.bundles.compose.materials)
    implementation(libs.bundles.composes)
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))

    // Room components
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    // Lifecycle components
    implementation(libs.lifecycle.runtime)

    // DataStore Preferences
    implementation(libs.androidx.datastore.preferences)

    // Dagger-hilt components
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.androidx.hilt.compiler)
}
