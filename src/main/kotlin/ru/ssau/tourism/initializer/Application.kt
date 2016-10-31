package ru.ssau.tourism.initializer

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * @since 31.10.16.
 */
@SpringBootApplication
open class Application {
}

fun main(args: Array<String>) {
	SpringApplication.run(Application::class.java, *args)
}
