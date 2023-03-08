package hello.servelet.basic.request

import com.fasterxml.jackson.databind.ObjectMapper
import hello.servelet.basic.HelloData
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets

@WebServlet(name = "RequestBodyJsonServlet", urlPatterns = ["/request-body-json"])
class RequestBodyJsonServlet: HttpServlet() {

    private val objectMapper: ObjectMapper = ObjectMapper()


    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        val inputStream = request.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        println("messageBody = $messageBody")
        val helloData = objectMapper.readValue(messageBody, HelloData::class.java)

        println("helloData.username = ${helloData.username}")
        println("helloData.age = ${helloData.age}")

        response.writer.write("ok")
    }
}