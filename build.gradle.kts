plugins {
    java
    id("org.springframework.boot") version "2.2.6.RELEASE" apply false
    id("com.google.cloud.tools.jib") version "2.1.0" apply false
}

group = "com.rmurugaian.spring"

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "eclipse")
    apply(plugin = "groovy")
    apply(plugin = "jacoco")
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

        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        testImplementation("org.codehaus.groovy:groovy-all:2.4.15")
        testImplementation("org.spockframework:spock-core:1.2-groovy-2.4")
        testImplementation("org.spockframework:spock-spring:1.2-groovy-2.4")
        testImplementation("com.athaydes:spock-reports:1.7.1") {
            isTransitive = false
        }
        testImplementation("org.testcontainers:mssqlserver:1.13.0")
        testImplementation("org.testcontainers:spock:1.13.0")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")
    }

    configure<org.springframework.boot.gradle.dsl.SpringBootExtension> {
        buildInfo()
    }

    tasks.named("jacocoTestReport", JacocoReport::class) {
        reports {
            xml.isEnabled = false
            csv.isEnabled = false
        }
    }

    tasks.named("jacocoTestCoverageVerification", JacocoCoverageVerification::class) {
        violationRules {
            rule {
                limit {
                    minimum = "0.5".toBigDecimal()
                }
            }

            rule {
                enabled = false
                element = "CLASS"
                includes = listOf("org.gradle.*")

                limit {
                    counter = "LINE"
                    value = "TOTALCOUNT"
                    maximum = "0.3".toBigDecimal()
                }
            }
        }
    }

}

configure(subprojects - project(":spring-boot-activemq-integration")) {
    apply(plugin = "com.google.cloud.tools.jib")

    configure<com.google.cloud.tools.jib.gradle.JibExtension> {
        val dockerRepo = project.property("dockerRepo") as String
        from {
            image = "adoptopenjdk/openjdk11"
        }
        to {
            image = dockerRepo.plus("/").plus(project.name)
            tags = setOf(project.version as String)
        }
        container {
            creationTime = "USE_CURRENT_TIMESTAMP"
            jvmFlags = listOf("-Djava.security.egd=file:/dev/./urandom")
        }
    }
}

