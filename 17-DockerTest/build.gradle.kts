plugins {
    java
    application
}

application {
    mainClass.set("com.app.Main")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
    archiveFileName.set("myapp.jar")
}