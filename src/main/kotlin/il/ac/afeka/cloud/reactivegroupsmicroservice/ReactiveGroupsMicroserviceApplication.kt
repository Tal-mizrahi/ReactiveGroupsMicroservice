package il.ac.afeka.cloud.reactivegroupsmicroservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactiveGroupsMicroserviceApplication

fun main(args: Array<String>) {
	runApplication<ReactiveGroupsMicroserviceApplication>(*args)
}
