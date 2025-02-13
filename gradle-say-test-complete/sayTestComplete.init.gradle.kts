allprojects {
    val sayTestComplete = tasks.register("_sayTestComplete") {
        doLast {
            exec {
                commandLine("say", "Test complete")
            }
        }
    }

    if (project.hasProperty("sayTestComplete")) {
        tasks.withType<Test>().configureEach {
            finalizedBy(sayTestComplete)
        }
    }
}
