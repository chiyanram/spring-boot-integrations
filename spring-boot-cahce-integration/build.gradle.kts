dependencies {
    "implementation"("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    "implementation"("com.hazelcast:hazelcast")
    "implementation"("com.hazelcast:hazelcast-spring")

    runtimeOnly("com.h2database:h2")
}
