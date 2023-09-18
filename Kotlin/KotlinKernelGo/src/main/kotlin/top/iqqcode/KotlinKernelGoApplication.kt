package top.iqqcode

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinKernelGoApplication

fun main(args: Array<String>) {
    runApplication<KotlinKernelGoApplication>(*args)
}
