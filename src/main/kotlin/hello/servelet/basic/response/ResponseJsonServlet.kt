package hello.servelet.basic.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonSerializable
import com.fasterxml.jackson.databind.ObjectMapper
import hello.servelet.basic.HelloData
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "ResponseJsonServlet", urlPatterns = ["/response-json"])
class ResponseJsonServlet: HttpServlet() {

    val objectMapper = ObjectMapper()

    override fun service(request: HttpServletRequest, response:HttpServletResponse) {
        response.contentType = "application/json"
        response.characterEncoding = "utf-8"

//        val message = "{" +
//                "\"name\" : \" kwon\" ," +
//                "\"age\" : 23" +
//                "}"

        val helloData = HelloData()
        helloData.username = "kwon"
        helloData.age = 23

        val message = objectMapper.writeValueAsString(helloData)

        val writer = response.writer
        writer.println(message)

        response.status = HttpServletResponse.SC_OK
    }
}