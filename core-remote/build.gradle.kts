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

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit2_version")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")
//    implementation "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3"
    // gson
    implementation("com.squareup.retrofit2:converter-gson:$retrofit2_version")
    // sanwich
    api("com.github.skydoves:sandwich:1.3.0")
}