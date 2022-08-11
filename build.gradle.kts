import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea

    id("org.springframework.boot") version "2.6.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.diffplug.spotless") version "6.4.2"

    jacoco
    id("org.sonarqube") version "3.3"

    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

group = "org.playtime"

version = "0.0.1-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_17

sourceSets {
    create("integrationTest") {
        kotlin {
            compileClasspath += main.get().output + configurations.testRuntimeClasspath
            runtimeClasspath += output + compileClasspath
        }
    }
}

idea {
    module {
        testSourceDirs = testSourceDirs.plus(sourceSets["integrationTest"].allJava.srcDirs)
        testResourceDirs = testResourceDirs.plus(sourceSets["integrationTest"].resources.srcDirs)
    }
}

repositories { mavenCentral() }

dependencies {
    implementation(project(":registration"))
    implementation(project(":infrastructure"))
    implementation(project(":sharedkernel"))

    implementation("org.keycloak:keycloak-admin-client:19.0.1")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-mail")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
        exclude(module = "mockito-core")
    }
    testImplementation("com.ninja-squad:springmockk:3.1.1")
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:3.4.8")
    testImplementation(libs.mockk)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

task<Test>("integrationsTests") {
    description = "Runs the integrations tests."
    group = "verification"
    testClassesDirs = sourceSets["integrationTest"].output.classesDirs
    classpath = sourceSets["integrationTest"].runtimeClasspath
    mustRunAfter(tasks["test"])
}

spotless {
    kotlin {
        target("**/*.kts", "**/*.kt")
        ktfmt().kotlinlangStyle()
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "Skanky0815_playtime")
        property("sonar.organization", "skanky0815")
        property("sonar.host.url", "https://sonarcloud.io")
        property(
            "sonar.exclusions",
            "./**/src/test/**,./src/main/kotlin/org/playtime/system/SystemApplication.kt"
        )
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "build/reports/jacoco/test/jacocoTestReport.xml"
        )
    }
}

subprojects {
    group = "org.playtime"
    version = "1.0-SNAPSHOT"

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "com.diffplug.spotless")
    apply(plugin = "jacoco")

    repositories { mavenCentral() }

    dependencies { testImplementation(rootProject.libs.bundles.junit) }

    tasks.withType<Test> {
        useJUnitPlatform()
        finalizedBy(tasks.jacocoTestReport)
    }

    tasks.jacocoTestReport {
        dependsOn(tasks.test)
        reports { xml.required.set(true) }
    }
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports { xml.required.set(true) }
}

tasks.check { dependsOn(tasks.named<JacocoReport>("testCodeCoverageReport")) }
