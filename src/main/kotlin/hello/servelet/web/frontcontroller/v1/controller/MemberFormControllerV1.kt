package hello.servelet.web.frontcontroller.v1.controller

import hello.servelet.web.frontcontroller.v1.ControllerV1
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class MemberFormControllerV1: ControllerV1 {

    override fun process(request: HttpServletRequest, response: HttpServletResponse) {
        val viewPath: String = "/WEB-INF/views/new-form.jsp"
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }
}