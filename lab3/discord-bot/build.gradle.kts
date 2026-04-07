plugins {
    kotlin("jvm") version "1.9.20"
    id("io.ktor.plugin") version "2.3.5"
    application
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("dev.kord:kord-core:0.13.1")
    implementation("org.slf4j:slf4j-simple:2.0.7")
}

application {
    mainClass.set("com.example.ApplicationKt")
}