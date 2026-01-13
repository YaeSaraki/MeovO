@file:Suppress("PropertyName", "SpellCheckingInspection")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import io.izzel.taboolib.gradle.Basic
import io.izzel.taboolib.gradle.BukkitFakeOp
import io.izzel.taboolib.gradle.BukkitHook
import io.izzel.taboolib.gradle.BukkitUI
import io.izzel.taboolib.gradle.BukkitUtil
import io.izzel.taboolib.gradle.CommandHelper
import io.izzel.taboolib.gradle.I18n
import io.izzel.taboolib.gradle.MinecraftChat
import io.izzel.taboolib.gradle.MinecraftEffect
import io.izzel.taboolib.gradle.Bukkit
import io.izzel.taboolib.gradle.IOC
import io.izzel.taboolib.gradle.Metrics
import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8

plugins {
    java
    id("io.izzel.taboolib") version "2.0.27"
    id("org.jetbrains.kotlin.jvm") version "2.2.0"
}

taboolib {
    env {
        install(Basic)
        install(BukkitFakeOp)
        install(BukkitHook)
        install(BukkitUI)
        install(BukkitUtil)
        install(CommandHelper)
        install(I18n)
        install(MinecraftChat)
        install(MinecraftEffect)
        install(Metrics)
        install(Bukkit)
        install(IOC)
    }
    description {
        name = "MeovO"
        contributors {
            name("YaeSaraki")
            desc("为MinecraftServer玩家提供附加功能")
        }
    }
    version { taboolib = "6.2.4-41dd260" }
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("ink.ptms.core:v12004:12004:mapped")
    compileOnly("ink.ptms.core:v12004:12004:universal")
    compileOnly(kotlin("stdlib"))
    compileOnly(fileTree("libs"))

    // http server
    compileOnly("org.nanohttpd:nanohttpd:2.3.1")

    // json
    compileOnly("com.google.code.gson:gson:2.10.1")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        jvmTarget.set(JVM_1_8)
        freeCompilerArgs.add("-Xjvm-default=all")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}