import com.google.protobuf.gradle.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.protobuf") version("0.8.17")
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

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.14.0"
    }

    // Generates the java Protobuf-lite code for the Protobufs in this project. See
    // https://github.com/google/protobuf-gradle-plugin#customizing-protobuf-compilation
    // for more information.
    generateProtoTasks {
        all().forEach { task ->
            task.plugins{
                create("java") {
                    option("lite")
                }
            }
        }
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

    implementation("androidx.datastore:datastore:1.0.0")
    implementation("com.google.protobuf:protobuf-javalite:3.18.0")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-compiler:$hilt_version")
}