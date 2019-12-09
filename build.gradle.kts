plugins {
    java
    groovy
    id("org.springframework.boot") version "2.2.0.RELEASE" apply false
}

group = "com.rmurugaian.spring"

repositories {
    mavenCentral()
}

val springCloudVersion = "Grrenwich.SR3"
val lombokVersion = "1.18.4"

subprojects {
    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "eclipse")
    apply(plugin = "groovy")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    repositories {
        mavenCentral()
        jcenter()
    }

    dependencies {
        implementation("com.google.guava:guava:28.1-jre")
        implementation("org.apache.commons:commons-lang3:3.9")
        implementation("org.joda:joda-money:1.0.1")
        implementation("io.springfox:springfox-swagger2:2.9.2")
        implementation("io.springfox:springfox-swagger-ui:2.9.2")
        compileOnly("io.swagger:swagger-annotations:1.6.0")

        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-actuator")

        implementation("com.fasterxml.jackson.datatype:jackson-datatype-guava")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-joda")
        compileOnly("org.springframework.boot:spring-boot-configuration-processor")

        compileOnly("org.projectlombok:lombok:${lombokVersion}")
        annotationProcessor("org.projectlombok:lombok:${lombokVersion}")

        testImplementation("org.codehaus.groovy:groovy-all:2.4.15")
        testImplementation("org.spockframework:spock-core:1.2-groovy-2.4")
        testImplementation("org.spockframework:spock-spring:1.2-groovy-2.4")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")
    }

}


