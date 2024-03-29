plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.jeckonly.pokemons"
        minSdk = 23
        targetSdk = 32
        versionCode = 2
        versionName = "v1.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val release = getByName("release")
        release.apply {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = compose_version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}


dependencies {
    // modules
    implementation(project(":core-data"))

    implementation("androidx.core:core-ktx:$core_version")

    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
    debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_version")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    implementation("androidx.activity:activity-compose:$activity_version")
    addTestImpl()

    // Compose dependencies)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    // implementation("androidx.compose.material:material-icons-extended:$compose_version")
    implementation("com.google.accompanist:accompanist-systemuicontroller:$acompanist_version")
    // To use constraintlayout in compose)
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // 官方导航库)
    implementation("androidx.navigation:navigation-compose:2.5.2")

    //Dagger - Hilt)
    addHiltImpl()
    implementation("androidx.hilt:hilt-navigation-compose:$androidx_hilt_version")

    // coil)
    implementation("io.coil-kt:coil-compose:2.1.0")
    // palette)
    implementation("androidx.palette:palette-ktx:1.0.0")

    // splash screen兼容)
    implementation("androidx.core:core-splashscreen:1.0.0")

    // work with WorkManager)
    implementation("androidx.hilt:hilt-work:$androidx_hilt_version")
    // When using Kotlin.)
    kapt("androidx.hilt:hilt-compiler:$androidx_hilt_version")

    // WorkManager: Kotlin + coroutines)
    implementation("androidx.work:work-runtime-ktx:$work_version")

    // compose view pager)
    implementation("com.google.accompanist:accompanist-pager:$acompanist_version")
    // If using indicators, also depend on)
    implementation("com.google.accompanist:accompanist-pager-indicators:$acompanist_version")

    // debugImplementation("com.squareup.leakcanary:leakcanary-android:2.9.1")
}