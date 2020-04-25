import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-gradle-plugin`
    kotlin("jvm") version embeddedKotlinVersion
}

gradlePlugin {
    plugins {
        create("myPlugins") {
            id = "my-plugin"
            implementationClass = "com.github.imanushin.Kt38253Plugin"
        }
    }
}

repositories {
    jcenter()
}

dependencies {
    api(kotlin("stdlib-jdk8"))

//    api(project(":jvm:basic:jvm-simple-script:script"))
    api("org.jetbrains.kotlin:kotlin-scripting-jvm:$embeddedKotlinVersion")
    compileOnly("org.jetbrains.kotlin:kotlin-scripting-jvm-host:$embeddedKotlinVersion")
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-scripting-jvm-host-embeddable:$embeddedKotlinVersion")
    testImplementation("io.kotest:kotest-runner-junit5:4.0.4")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    test {
        useJUnitPlatform()
    }
}