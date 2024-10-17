plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.teachmintassignment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.teachmintassignment"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.compose.ui:ui:1.3.1") // UI
    implementation("androidx.compose.material3:material3:1.0.1") // Material Design Components
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.1") // Preview tool

    implementation("androidx.activity:activity-compose:1.7.0") // Compose support for Activity

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2") // ViewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2") // LiveData

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0") // Coroutines core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0") // Coroutines for Android

    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Gson converter for Retrofit

    implementation("androidx.room:room-ktx:2.5.0") // Room extensions for Kotlin
    implementation("androidx.room:room-runtime:2.5.0")
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.monitor)
    implementation(libs.androidx.junit.ktx)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.testng) // Room runtime
    kapt("androidx.room:room-compiler:2.5.0") // Room compiler

    implementation("androidx.webkit:webkit:1.4.0") // WebView support

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.compose.ui:ui:1.5.1")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2") // Add this line
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("androidx.compose.ui:ui:1.3.1")
    implementation("androidx.compose.material3:material3:1.0.0-alpha16")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
}