import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("me.kartikarora.icanhazstream.library")
                apply("me.kartikarora.icanhazstream.compose")
            }

            tasks.withType<Test>().configureEach {
                useJUnitPlatform()
            }

            dependencies {
                add("implementation", project(":core:model"))
                add("implementation", project(":core:data"))
                add("implementation", project(":core:ui"))

                add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
                add("implementation", libs.findLibrary("androidx-lifecycle-runtime-compose").get())
                add("implementation", libs.findLibrary("kotlinx-coroutines-android").get())

                add("testImplementation", project(":core:testing"))
                add("testImplementation", libs.findLibrary("junit5-api").get())
                add("testRuntimeOnly", libs.findLibrary("junit5-engine").get())
                add("testRuntimeOnly", libs.findLibrary("junit-platform-launcher").get())
            }
        }
    }
}

private val Project.libs
    get() = extensions.getByType(
        org.gradle.api.artifacts.VersionCatalogsExtension::class.java
    ).named("libs")
