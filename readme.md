# Sample for [KT-38253](https://youtrack.jetbrains.com/issue/KT-38253)

This is example to reproduce Kotlin Scripting issue [KT-38253](https://youtrack.jetbrains.com/issue/KT-38253).

Preconditions:
1. Java Home should be configured JVM 11 (probably 8 fails too)

**Scenario 1 - Gradle console:**
1. Open console at the project root
2. Run ```.\gradlew.bat test``` for Windows and ```gradlew test``` for Linux/Mac. I checked for Windows platform only. 

**Expected result:**
Task is green, everything works

**Actual result:**
Task fails, please check [test-results.html](./test-results.html) file.

**Scenario 2 - IntelliJ Idea**
1. Open project in IntelliJ Idea 2019.3 or in 2020.1 (the result is the same)
2. Configure Gradle running as (just to simplify debugging):
2.1. Build and Run using Gradle
2.2. Test using IntelliJ Idea
3. Run test ```PluginEvaluationTest```

**Expected result:**
Test is green, everything works

**Actual result:**
Task fails, please check [test-results.html](./test-results.html) file.

**What is here**

This is simple Gradle plugin, which tries to execute task ```compileCustomScript```. This task should execute script ```println(...)```.

This code works well in standalone application, however it fails from Gradle classpath.

Looks like the root cause is with message ```kotlinx/coroutines/BuildersKt```, because Kotlin Compiler use too old coroutines libraty - v. 1.2.1 only