import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("idea")

    id("org.springframework.boot") version "2.6.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.diffplug.spotless") version "6.4.2"

    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

group = "org.playtime"

version = "0.0.1-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_17

sourceSets {
    create("integrationTest") {
        withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
            kotlin.srcDir("src/integrationTest/kotlin")
            resources.srcDir("src/integrationTest/resources")
            compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
            runtimeClasspath += output + compileClasspath + sourceSets["test"].runtimeClasspath
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

    implementation("org.keycloak:keycloak-admin-client:18.0.0")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-mail:2.6.7")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
        exclude(module = "mockito-core")
    }
    testImplementation("com.ninja-squad:springmockk:3.1.1")
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:3.4.5")
    testImplementation(libs.mockk)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> { useJUnitPlatform() }

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

subprojects {
    group = "org.playtime"
    version = "1.0-SNAPSHOT"

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "com.diffplug.spotless")

    repositories { mavenCentral() }

    dependencies { testImplementation(rootProject.libs.bundles.junit) }

    tasks.withType<Test> { useJUnitPlatform() }
}
