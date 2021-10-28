import kotlinx.coroutines.*

fun main(args: Array<String>) {
    runBlocking {
        val scopeJob = Job()
        val scope = CoroutineScope(Dispatchers.Default + scopeJob)
        val coroutine1 = scope.launch {
            delay(500)
            println("I'm the first coroutine")
        }
        val coroutine2 = scope.launch {
            delay(1000)
            println("I'm the second coroutine")
        }

        //coroutine1.join() //so the app never exits until the coroutine1 is complete
        val coroutines = listOf(coroutine1, coroutine2)
        
        println("List of coroutine jobs")
        for (coroutine in coroutines) {
            println(coroutine)
        }

        //referencing coroutines using job
        println("Children of CoroutineScope")
        for (child in scopeJob.children) {
            println(child)
        }
        coroutines.joinAll() //so the app never exits until the coroutine is complete

        coroutineScope {
            launch {
                delay(500)
                println("I'm the coroutineScope builder")
            }
        }
    }
}