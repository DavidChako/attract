import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    application
}

group = "com.icosahedron"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
//    implementation("org.jetbrains.skija:skija-shared:0.93.6")
//    implementation("org.jetbrains.skija:skija-macos-arm64:0.93.6")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}