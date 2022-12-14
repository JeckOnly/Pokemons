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

// Allow(references to generated code)
kapt {
    correctErrorTypes = true
}

dependencies {

    // modules
    api(project(":core-model"))
    api(project(":util"))
    implementation(project(":core-remote"))
    implementation(project(":core-database"))
    implementation(project(":core-datastore"))
    implementation("androidx.core:core-ktx:$core_version")
    addTestImpl()

    //Dagger(- Hilt)
    addHiltImpl()

    // work(with WorkManager)
    implementation("androidx.hilt:hilt-work:$androidx_hilt_version")
    // When(using Kotlin.)
    kapt("androidx.hilt:hilt-compiler:$androidx_hilt_version")


    // WorkManager: Kotlin(+ coroutines)
    implementation("androidx.work:work-runtime-ktx:$work_version")
}