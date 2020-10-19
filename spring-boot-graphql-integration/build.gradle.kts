dependencies {

    implementation("com.graphql-java:graphql-spring-boot-starter:5.0.2")
    implementation("com.graphql-java:graphql-java-tools:5.2.4")
    //implementation("com.graphql-java:graphql-java-annotations:3.0.3")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("com.h2database:h2")
    //implementation("com.microsoft.sqlserver:mssql-jdbc")

    testImplementation("com.graphql-java:graphql-spring-boot-starter-test:5.0.2")
}