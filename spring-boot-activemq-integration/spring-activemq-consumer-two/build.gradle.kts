apply(plugin = "groovy")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-activemq")
    implementation("org.apache.activemq:activemq-broker")
    implementation("org.springframework.boot:spring-boot-starter-integration")
}