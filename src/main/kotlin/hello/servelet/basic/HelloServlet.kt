package hello.servelet.basic

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "helloServlet", urlPatterns = ["/hello"])
class HelloServlet:HttpServlet() {

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        println("HelloServlet.service")
        println("request = ${request}")
        println("response = ${response}")


        val username = request?.getParameter("username")
        println("username = ${username}")

        response?.contentType = "text/plain"
        response?.characterEncoding = "utf-8"
        response?.writer?.write("hello $username")

    }
}