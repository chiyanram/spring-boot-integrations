plugins {
    java
    groovy
    id("org.springframework.boot") version "2.0.6.RELEASE" apply false
}

group = "com.rmurugaian.spring"

repositories {
    mavenCentral()
}

val springCloudVersion = "Grrenwich.SR3"

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
    }

    configurations {
        compileJava{
            extendsFrom annotationProcessor
        }
    }
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-actuator")

        runtimeOnly("org.springframework.boot:spring-boot-devtools")

        compileOnly("org.springframework.boot:spring-boot-configuration-processor")
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        testImplementation("org.codehaus.groovy:groovy-all:2.4.15")
        testImplementation("org.spockframework:spock-core:1.2-groovy-2.4")
        testImplementation("org.spockframework:spock-spring:1.2-groovy-2.4")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")
    }


}


