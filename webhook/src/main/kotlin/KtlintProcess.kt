import io.vertx.core.buffer.Buffer
import io.vertx.core.json.JsonArray
import io.vertx.core.logging.LoggerFactory
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

fun main() {
    val errors = processFile("""
    fun expensiveWork(): Int {
        Thread.sleep(1000)
        return Random.nextInt()
    }

    """.trimIndent())
    println(errors)
}

fun processFile(fileContent: String): JsonArray {

    val processBuilder = ProcessBuilder(
            "ktlint",
            "--reporter=json",
            "--stdin")

    processBuilder.redirectErrorStream(true)

    val process = processBuilder.start()

    process.outputStream.write(fileContent.toByteArray(Charset.forName("UTF-8")))
    process.outputStream.flush()
    process.outputStream.close()

    process.waitFor(5, TimeUnit.SECONDS)

    val bytes = process.inputStream.readAllBytes()

    val exitValue = process.exitValue()
    LoggerFactory.getLogger("Ktlint Process").debug("Exit value: $exitValue")

    return JsonArray(Buffer.buffer(bytes))
}