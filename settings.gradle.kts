rootProject.name = "playtime"

include("registration", "infrastructure", "sharedkernel")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("junit-core", "junit:junit:4.13.2")
            library("junit-jupiter", "org.junit.jupiter:junit-jupiter:5.8.2")
            library("mockk", "io.mockk:mockk:1.12.3")
            library("faker", "io.github.serpro69:kotlin-faker:1.10.0")

            bundle("junit", listOf("junit-core", "junit-jupiter", "mockk", "faker"))
        }
    }
}
