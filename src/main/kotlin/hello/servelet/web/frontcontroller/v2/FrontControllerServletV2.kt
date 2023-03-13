package hello.servelet.web.frontcontroller.v2

import hello.servelet.web.frontcontroller.v2.controller.MemberFormControllerV2
import hello.servelet.web.frontcontroller.v2.controller.MemberListControllerV2
import hello.servelet.web.frontcontroller.v2.controller.MemberSaveControllerV2
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory

@WebServlet(name = "frontControllerServletV2", urlPatterns = ["/front-controller/v2/*"])
class FrontControllerServletV2: HttpServlet() {

    val logger = LoggerFactory.getLogger(FrontControllerServletV2::class.java)

    val controllerMap :MutableMap<String, ControllerV2> = HashMap()

    init {
        controllerMap.put("/front-controller/v2/members/new-form", MemberFormControllerV2())
        controllerMap.put("/front-controller/v2/members/save", MemberSaveControllerV2())
        controllerMap.put("/front-controller/v2/members", MemberListControllerV2())
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        val requestURI = request.requestURI

        val controller = controllerMap[requestURI]

        if (controller == null) {
            response.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        val view = controller.process(request, response)
        view.render(request, response)
    }
}