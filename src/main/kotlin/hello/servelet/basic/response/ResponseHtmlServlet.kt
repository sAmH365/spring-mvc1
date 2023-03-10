package hello.servelet.basic.response

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "ResponseHtmlServlet", urlPatterns = ["/response-html"])
class ResponseHtmlServlet: HttpServlet() {

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        // Content-Type: text/html; charset=utf-8
        response.contentType = "text/html"
        response.characterEncoding = "utf-8"

        val writer = response.writer
        writer.println("<html>")
        writer.println("<body>")
        writer.println("hihihihi")
        writer.println("</body>")
        writer.println("</html>")

        response.status = HttpServletResponse.SC_OK
    }
}