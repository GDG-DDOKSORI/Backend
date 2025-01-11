plugins {
	java
	id("org.springframework.boot") version "3.3.1"
	id("io.spring.dependency-management") version "1.1.6"
	kotlin("jvm") version "1.9.10"
	kotlin("plugin.spring") version "1.9.10"
}

group = "com.bucketNote"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// QueryDSL
	implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
	annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta")

	// Jakarta Persistence API
	implementation("jakarta.persistence:jakarta.persistence-api:3.1.0") // JPA 관련 Jakarta API
	annotationProcessor("jakarta.persistence:jakarta.persistence-api")
	annotationProcessor("jakarta.annotation:jakarta.annotation-api")
	//redis
	implementation("org.springframework.boot:spring-boot-starter-data-redis")

	// JSON Web Token 라이브러리
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
	implementation("io.jsonwebtoken:jjwt-jackson:0.11.5") // Jackson 기반 JSON 처리

	// Spring Boot Starters
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")

	// Thymeleaf Extras
	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")

	// SpringDoc
	// SpringDoc
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.3")
	 // 최신 버전 강제
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")


	// MySQL Driver
	runtimeOnly("com.mysql:mysql-connector-j")

	// Test Libraries
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(module = "mockito-core") // 충돌 방지
	}
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


tasks.withType<Test> {
	useJUnitPlatform()
}
