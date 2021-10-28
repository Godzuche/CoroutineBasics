import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun main (args: Array<String>) {

    runBlocking {
        var i = 0

        //this CoroutineScope builder is used to set a timeout for the coroutine and it throws a Logged TimeoutCancellationException
        withTimeout(1000) {
            while (true){
                delay(100)
                println(i)
                i++
            }
        }
    }
}