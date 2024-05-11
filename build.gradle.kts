plugins {
    id("java")
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "org.spring.data.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
//    implementation ("org.springframework.boot:spring-boot-starter-web")
//    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")

    //Spring base
    implementation("org.springframework:spring-context")

    //Data base
    implementation("org.springframework:spring-jdbc")
    implementation("com.h2database:h2")
    implementation("com.zaxxer:HikariCP:5.0.1")


    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}