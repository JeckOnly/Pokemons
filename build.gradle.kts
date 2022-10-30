buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hilt_version")
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
//    addProjectPlugin()
    id("com.android.application").version("7.2.1").apply(false)
    id("com.android.library").version("7.2.1").apply(false)
    id("org.jetbrains.kotlin.android").version("1.7.0").apply(false)
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}