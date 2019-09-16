import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-activemq")
    implementation("org.springframework.boot:spring-boot-starter-integration")
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework,boot")
}
tasks {
    named("bootJar", BootJar::class) {
        enabled = false
    }
}