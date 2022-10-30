import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.PluginDependenciesSpecScope


fun DependencyHandlerScope.addTestImpl() {
    "testImplementation"("junit:junit:$junit_version")
    "androidTestImplementation"("androidx.test.ext:junit:$test_ext_version")
    "androidTestImplementation"("androidx.test.espresso:espresso-core:$test_espresso_version")
}

fun DependencyHandlerScope.addHiltImpl() {
    "implementation"("com.google.dagger:hilt-android:$hilt_version")
    "kapt"("com.google.dagger:hilt-compiler:$hilt_version")
}

fun PluginDependenciesSpecScope.addProjectPlugin() {
    id("com.android.application").version("7.2.1").apply(false)
    id("com.android.library").version("7.2.1").apply(false)
    id("org.jetbrains.kotlin.android").version("1.7.0").apply(false)
}
