import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.71"
	kotlin("plugin.spring") version "1.3.71"
	id("com.github.ben-manes.versions") version "0.28.0"

	//id("net.thauvin.erik.gradle.semver") version "1.0.4"
	//id ("de.gliderpilot.semantic-release") version "1.0.0"
	//id("pl.allegro.tech.build.axion-release") version "1.11.0"
	//id("ch.netzwerg.release") version "1.2.3"
	id("net.researchgate.release") version "2.6.0"
	//id("org.ajoberstar.reckon") version "0.12.0"
}


group = "com.github.alexandrenavarro"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}


dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		//exclude(group = "org.mockito", module = "mockito-junit-jupiter")

	}
	//testImplementation("org.junit.jupiter:junit-jupiter:5.6.0")
}

configurations.all {
	resolutionStrategy {
		failOnVersionConflict()
		preferProjectModules()
		dependencySubstitution {
			substitute(module("org.junit.jupiter:junit-jupiter")).with(module("org.junit.jupiter:junit-jupiter:5.6.0"))
		}
	}
}


tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
