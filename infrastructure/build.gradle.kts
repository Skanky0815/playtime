dependencies {
    implementation(project(":registration"))
    implementation(project(":sharedkernel"))

    implementation("org.keycloak:keycloak-admin-client:17.0.1")
    implementation("org.springframework.boot:spring-boot-starter-mail:2.6.6")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:2.6.6")
}
