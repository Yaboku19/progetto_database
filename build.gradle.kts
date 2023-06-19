plugins {
    java
    application
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

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    implementation("mysql:mysql-connector-java:8.0.29")
}

application {
    mainClass.set("futureodissey.App")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
