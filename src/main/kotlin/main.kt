import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {

    runBlocking {
        launch {
            delayAndPrintln(500, "I'm the first println")
        }
        launch {
            delayAndPrintln(1000, "I'm the middle println")
        }
        println("I'm the last println")
    }

}

suspend fun delayAndPrintln(delay: Long, message: String) {
    delay(delay)
    println(message)
}