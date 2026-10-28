plugins {
    kotlin("jvm") version "2.4.21"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jgrapht:jgrapht-core:1.5.3")
}

kotlin {
    jvmToolchain(17)

    sourceSets {
        main {
            kotlin.srcDirs("src")
        }
    }
}

tasks.wrapper {
    gradleVersion = "9.7.0"
    distributionSha256Sum = "84fbba45c7f4c64abc77460e1c00f541e9f960e3c7ed2538f1ede19eacd873ae"
}
