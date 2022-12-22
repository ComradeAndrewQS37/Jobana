import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.spring") version "1.7.21"
    kotlin("plugin.jpa") version "1.7.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation ("org.hibernate.validator:hibernate-validator:8.0.0.Final")


    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-crypto:5.7.3")
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation ("javax.xml.bind:jaxb-api:2.2.4")
    //implementation("org.springframework.security:spring-security-config:5.2.1.RELEASE")
    //implementation ("javax.servlet:javax.servlet-api:3.0.1")
    //implementation("org.springframework.boot:spring-boot-starter-parent:1.2.1.RELEASE")



    runtimeOnly("org.postgresql:postgresql")
    //runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
