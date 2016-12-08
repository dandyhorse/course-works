project {
    modelVersion '4.0.0'
    groupId 'ru.ssau'
    artifactId 'realtor'
    version '0.1'
    name 'Realtor app'

    properties {
        java {
            version '1.8'
        }
    }

    parent {
        groupId 'org.springframework.boot'
        artifactId 'spring-boot-starter-parent'
        version '1.4.2.RELEASE'
    }

    dependencies {
        dependency {
            groupId 'org.springframework.boot'
            artifactId 'spring-boot-starter-data-jpa'
        }
        dependency {
            groupId 'org.springframework.boot'
            artifactId 'spring-boot-starter-test'
            scope 'test'
        }
        dependency {
            groupId 'com.h2database'
            artifactId 'h2'
        }
    }

    build {
        plugins {
            plugin {
                groupId 'org.springframework.boot'
                artifactId 'spring-boot-maven-plugin'
            }
            plugin {
                groupId 'org.springframework.boot'
                artifactId 'spring-boot-maven-plugin'
            }
        }

        $execute(id: 'hello', phase: 'validate') {
            println ""
            println "Hello! I am Groovy inside Maven. What? What am I doing here?? I'm confused. I guess we are friends now. Maybe."
            println ""
        }
    }
}