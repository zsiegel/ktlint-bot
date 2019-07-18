import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.ext.web.Router
import io.vertx.kotlin.coroutines.awaitBlocking
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import kotlin.random.Random

fun main() {

    val options = VertxOptions()
            .setWarningExceptionTime(1)
            .setMaxEventLoopExecuteTime(500)
            .setMaxEventLoopExecuteTimeUnit(TimeUnit.MILLISECONDS)
            .setBlockedThreadCheckInterval(500)
            .setBlockedThreadCheckIntervalUnit(TimeUnit.MILLISECONDS)

    val vertx = Vertx.vertx(options)
    val server = vertx.createHttpServer()
    val router = Router.router(vertx)

    val route = router.route()

    route.handler { ctx ->
        CoroutineScope(ctx.vertx().dispatcher()).launch {

            val work = awaitBlocking {
                println("${Thread.currentThread().name} Starting the expensive work")
                expensiveWork()
            }

            println("${Thread.currentThread().name} Responding to request")
            ctx.response().end(":) - $work")
        }
    }

    server.requestHandler(router).listen(8080)
}

fun expensiveWork(): Int {
    Thread.sleep(1000)
    return Random.nextInt()
}