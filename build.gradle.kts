plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.danilopianini.gradle-java-qa") version "0.43.0"
}

repositories {
    mavenCentral()
}

val javaFXModules = listOf(
    "base",
    "controls",
    "fxml",
    "swing",
    "graphics",
    "media"
)

val supportedPlatforms = listOf("linux", "mac", "win") // All required for OOP

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    implementation("mysql:mysql-connector-java:8.0.29")

    val javaFxVersion = 19

    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
        }
    }
}

application {
    mainClass.set("futureodissey.App")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
