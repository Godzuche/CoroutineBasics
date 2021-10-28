import kotlinx.coroutines.*
import java.security.InvalidParameterException

fun main (args: Array<String>) {

    runBlocking {
        //for the launch Coroutine builder, you can handle exceptions using only the CoroutineExceptionHandler
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("Caught: ${throwable.message}")
        }
        val scope = CoroutineScope(Dispatchers.Default)

        //pass the handler as a parameter
        val job = scope.launch(exceptionHandler) {
            throwExLaunch("I'm exceptional in Launch builder!", true) //pass in true to throw the exception.
        }
        job.join()

        //handling the exception using the try-catch. you can also add the finally block
        try{
            //using the same scope
            val deferred = scope.async {
                throwExAsync("I'm exceptional in Async builder!", true)
            }
            //to get the result of the deferred object
            val result = deferred.await()
            println(result)
        } catch (exception: InvalidParameterException) {
            println("Caught: ${exception.message}")
        }
    }
}

suspend fun throwExLaunch(message: String, throwIt: Boolean = false) {
    delay(500)
    if (throwIt) {
        throw InvalidParameterException ("I'm an exception")
    } else
        println(message)
}

suspend fun throwExAsync(message: String, throwIt: Boolean = false): String {
    delay(500)
    if (throwIt) {
        throw InvalidParameterException ("I'm an exception")
    } else
        return message
}