package com.github.imanushin

import org.gradle.api.Plugin
import org.gradle.api.Project
import kotlin.script.experimental.api.ScriptCompilationConfiguration
import kotlin.script.experimental.host.StringScriptSource
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost

class Kt38253Plugin : Plugin<Project> {
    override fun apply(target: Project) {
        val pluginTask = target.tasks.create(PluginConstants.taskName)

        pluginTask.doLast {
            BasicJvmScriptingHost().eval(
                    script = StringScriptSource("""
                        println("${PluginConstants.pluginOutputText}")
                    """.trimIndent()),
                    compilationConfiguration = ScriptCompilationConfiguration {
                        jvm {
                            dependenciesFromCurrentContext(
                                 //   "script", /* script library jar name */
                                    wholeClasspath = true
                            )
                        }
                    },
                    evaluationConfiguration = null
            )
        }
    }

}