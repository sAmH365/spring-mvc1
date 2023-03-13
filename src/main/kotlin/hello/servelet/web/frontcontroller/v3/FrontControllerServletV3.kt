package hello.servelet.web.frontcontroller.v3

import hello.servelet.web.frontcontroller.MyView
import hello.servelet.web.frontcontroller.v2.controller.MemberFormControllerV2
import hello.servelet.web.frontcontroller.v2.controller.MemberListControllerV2
import hello.servelet.web.frontcontroller.v2.controller.MemberSaveControllerV2
import hello.servelet.web.frontcontroller.v3.controller.MemberFormControllerV3
import hello.servelet.web.frontcontroller.v3.controller.MemberListControllerV3
import hello.servelet.web.frontcontroller.v3.controller.MemberSaveControllerV3
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory

@WebServlet(name = "frontControllerServletV3", urlPatterns = ["/front-controller/v3/*"])
class FrontControllerServletV3: HttpServlet() {

    val logger = LoggerFactory.getLogger(FrontControllerServletV3::class.java)

    val controllerMap :MutableMap<String, ControllerV3> = HashMap()

    init {
        controllerMap.put("/front-controller/v3/members/new-form", MemberFormControllerV3())
        controllerMap.put("/front-controller/v3/members/save", MemberSaveControllerV3())
        controllerMap.put("/front-controller/v3/members", MemberListControllerV3())
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        val requestURI = request.requestURI

        val controller = controllerMap[requestURI]

        if (controller == null) {
            response.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        val paramMap: MutableMap<String, String> = createParamMap(request)
        val mv = controller.process(paramMap)

        val viewName = mv.viewName
        val view = viewResolver(viewName)

        view.render(mv.model, request, response)
    }

    private fun viewResolver(viewName: String) = MyView("/WEB-INF/views/${viewName}.jsp")

    private fun createParamMap(request: HttpServletRequest): MutableMap<String, String> {
        val paramMap: MutableMap<String, String> = hashMapOf()
        request.parameterNames.asIterator()
            .forEachRemaining { paramName ->
                paramMap[paramName] = request.getParameter(paramName)
            }
        return paramMap
    }
}