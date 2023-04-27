import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}
repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("GradleCommonConfig") {
            id = "GradleCommonConfig"
            implementationClass = "GradleCommonConfig"
        }
    }
}