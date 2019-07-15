import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import java.util.concurrent.TimeUnit

fun main() {

    val options = VertxOptions()
            .setWarningExceptionTime(1)
            .setMaxEventLoopExecuteTime(500)
            .setMaxEventLoopExecuteTimeUnit(TimeUnit.MILLISECONDS)
            .setBlockedThreadCheckInterval(500)
            .setBlockedThreadCheckIntervalUnit(TimeUnit.MILLISECONDS)

    val vertx = Vertx.vertx(options)

    val server = vertx.createHttpServer()

    server.requestHandler { req ->
        req.response().end(":)")
    }

    server.listen(8080)
}