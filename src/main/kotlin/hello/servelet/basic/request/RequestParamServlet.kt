package hello.servelet.basic.request

import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

/**
 * 1. 파라미터 전송 기능
 * http:// localhost:8080/request-param?username=hello&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = ["/request-param"])
class RequestParamServlet: HttpServlet() {

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        println("[전체 파라미터 조회] - start")
        request.parameterNames.asIterator()
            .forEachRemaining { paramName -> println("$paramName = ${request.getParameter(paramName)}") }
        println("[전체 파라미터 조회] - end")
        println()

        println("[단일 파라미터 조회] - start")
        val userName = "${request.getParameter("username")}"
        println("userName = $userName")

        val age = "${request.getParameter("age")}"
        println("age = $age")

        println("[단일 파라미터 조회] - end")
        println()

        println("[이름이 같은 복수 파라미터 조회]")
        val usernames = request.getParameterValues("username")
        for (name in usernames) {
            println("username = $name")
        }

        response.writer.write("ok")

    }
}