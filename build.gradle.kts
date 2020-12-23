plugins {
    java
    id("org.springframework.boot") version "2.3.3.RELEASE" apply false
    id("com.google.cloud.tools.jib") version "2.1.0" apply false
}

group = "com.rmurugaian.spring"

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "groovy")
    apply(plugin = "jacoco")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    java {
        sourceCompatibility = JavaVersion.VERSION_14
        targetCompatibility = JavaVersion.VERSION_14
    }

    repositories {
        mavenCentral()
        jcenter()
    }

    dependencies {
        implementation("com.google.guava:guava:29.0-jre")
        implementation("org.apache.commons:commons-lang3:3.10")
        implementation("org.joda:joda-money:1.0.1")

        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-actuator")

        implementation("com.fasterxml.jackson.datatype:jackson-datatype-guava")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-joda")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        testImplementation("org.codehaus.groovy:groovy-all:2.4.15")
        testImplementation("org.spockframework:spock-core:1.2-groovy-2.4")
        testImplementation("org.spockframework:spock-spring:1.2-groovy-2.4")
        testImplementation("com.athaydes:spock-reports:1.7.1") {
            isTransitive = false
        }
        testImplementation("org.testcontainers:mssqlserver:1.14.1")
        testImplementation("org.testcontainers:spock:1.14.1")
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

    tasks {
        named("test", Test::class) {
            systemProperty("testContainers", project.properties["testContainers"] as String)
        }
    }

}

