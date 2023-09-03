plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.roomdb_kotlin"
    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.roomdb_kotlin"
        minSdk = 28
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packagingOptions {
        excludes += "META-INF/*"
    }
}

dependencies {

    //def room_version = "2.4.3"
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    /*// Navigation Component
    implementation "androidx.navigation:navigation-fragment-ktx:2.5.3"
    implementation "androidx.navigation:navigation-runtime-ktx:2.5.3"
    implementation "androidx.navigation:navigation-ui-ktx:2.5.3"

    // Room components
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-ktx:$room_version"
    androidTestImplementation "androidx.room:room-testing:$room_version"

    // Lifecycle components
    implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"
    implementation "androidx.lifecycle:lifecycle-common-java8:2.2.0"
    implementation "androidx.lifecycle:lifecycle-viewmodel:2.4.0"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"

    // Kotlin components
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.72"
    api "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5"
    api "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.5"*/
}