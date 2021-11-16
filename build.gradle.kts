import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
    kotlin("plugin.jpa") version "1.5.31"
    id("org.openapi.generator") version ("5.2.1")
}

group = "ir.amv.snippets"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

sourceSets.getByName("main") {
    java.srcDir(buildDir.resolve("generated/open-api-server/src/main/java"))
}

dependencies {
    // to fix OpenAPI generated code problems
    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("org.openapitools:jackson-databind-nullable:0.2.1")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val openApiGenTask = tasks.register<GenerateTask>("GenerateOpenApiSpec") {
    val input =
        rootDir.resolve("src/main/resources/static/user-api.yaml").absolutePath
    val output = buildDir.resolve("generated/open-api-server").absolutePath

    generatorName.set("spring")
    inputSpec.set(input)
    outputDir.set(output)
    apiPackage.set("ir.amv.os.snippets.generated.api")
    modelPackage.set("ir.amv.os.snippets.generated.model")
    ignoreFileOverride.set("${project.rootDir}/.openapi-generator-ignore")
    modelNameSuffix.set("Dto")

    inputs.files(input)
    outputs.dir(output)

    configOptions.set(
        mutableMapOf(
            "interfaceOnly" to "true",
            "artifactId" to "qlang-sample-server",
            "dateLibrary" to "java8",
            "serviceInterface" to "true",
            "hideGenerationTimestamp" to "true",
            "serializableModel" to "true",
            "skipValidateSpec" to "false",
            "enumPropertyNaming" to "UPPERCASE",
            "groupId" to "md.quin",
            "configPackage" to "ir.amv.os.snippets.generated.config",
            "basePackage" to "ir.amv.os.snippets.generated",
            "useTags" to "true"
        )
    )
}

tasks.compileJava.get().dependsOn(openApiGenTask)
