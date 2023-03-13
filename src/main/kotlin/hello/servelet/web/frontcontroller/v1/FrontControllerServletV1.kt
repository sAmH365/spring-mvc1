package hello.servelet.web.frontcontroller.v1

import hello.servelet.web.frontcontroller.v1.controller.MemberFormControllerV1
import hello.servelet.web.frontcontroller.v1.controller.MemberListControllerV1
import hello.servelet.web.frontcontroller.v1.controller.MemberSaveControllerV1
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory

@WebServlet(name = "frontControllerServletV1", urlPatterns = ["/front-controller/v1/*"])
class FrontControllerServletV1: HttpServlet() {

    val logger = LoggerFactory.getLogger(FrontControllerServletV1::class.java)

    val controllerMap :MutableMap<String, ControllerV1> = HashMap()

    init {
        controllerMap.put("/front-controller/v1/members/new-form", MemberFormControllerV1())
        controllerMap.put("/front-controller/v1/members/save", MemberSaveControllerV1())
        controllerMap.put("/front-controller/v1/members", MemberListControllerV1())
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        println("FrontControllerServletV1.service")

        val requestURI = request.requestURI

        val controller = controllerMap[requestURI]

//        println("requestURI = ${request.requestURI}")
//        println("controller = ${controller}")

        if (controller == null) {
            response.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        controller.process(request, response)
    }
}