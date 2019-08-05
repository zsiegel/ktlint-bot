import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.core.json.Json
import io.vertx.core.logging.LoggerFactory
import io.vertx.ext.web.Route
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.awaitBlocking
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import kotlin.random.Random

private val RoutingContext.exceptionhandler
    get() = CoroutineExceptionHandler { _, throwable ->
        LoggerFactory.getLogger("Exception").error("Unable to process request", throwable)
        fail(throwable)
    }

fun Route.coroutineHandler(fn: suspend (routingContext: RoutingContext) -> Any) {
    this.handler { ctx ->
        CoroutineScope(ctx.vertx().dispatcher() + ctx.exceptionhandler).launch {
            val response = fn(ctx)
            ctx.response().end(Json.encode(response))
        }
    }
}

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

    route.coroutineHandler {
        val work = awaitBlocking {
            println("${Thread.currentThread().name} Starting the expensive work")
            expensiveWork()
        }

        println("${Thread.currentThread().name} Responding to request")
        mapOf("message" to ":) - $work")
    }

    server.requestHandler(router).listen(8080)
}

fun expensiveWork(): Int {
    Thread.sleep(1000)
    return Random.nextInt()
}