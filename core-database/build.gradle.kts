plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {
    // modules
    implementation(project(":core-model"))

    implementation("androidx.core:core-ktx:$core_version")
    testImplementation("junit:junit:$junit_version")
    androidTestImplementation("androidx.test.ext:junit:$test_ext_version")
    androidTestImplementation("androidx.test.espresso:espresso-core:$test_espresso_version")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-compiler:$hilt_version")

    // Room
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")
}