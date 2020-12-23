import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks {
    named("bootJar", BootJar::class) {
        enabled = false
    }
}