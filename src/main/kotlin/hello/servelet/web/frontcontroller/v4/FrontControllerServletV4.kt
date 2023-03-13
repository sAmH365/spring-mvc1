package hello.servelet.web.frontcontroller.v4

import hello.servelet.web.frontcontroller.MyView
import hello.servelet.web.frontcontroller.v2.controller.MemberFormControllerV2
import hello.servelet.web.frontcontroller.v2.controller.MemberListControllerV2
import hello.servelet.web.frontcontroller.v2.controller.MemberSaveControllerV2
import hello.servelet.web.frontcontroller.v3.controller.MemberFormControllerV3
import hello.servelet.web.frontcontroller.v3.controller.MemberListControllerV3
import hello.servelet.web.frontcontroller.v3.controller.MemberSaveControllerV3
import hello.servelet.web.frontcontroller.v4.controller.MemberFormControllerV4
import hello.servelet.web.frontcontroller.v4.controller.MemberListControllerV4
import hello.servelet.web.frontcontroller.v4.controller.MemberSaveControllerV4
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory

@WebServlet(name = "frontControllerServletV4", urlPatterns = ["/front-controller/v4/*"])
class FrontControllerServletV4: HttpServlet() {

    val logger = LoggerFactory.getLogger(FrontControllerServletV4::class.java)

    val controllerMap :MutableMap<String, ControllerV4> = HashMap()

    init {
        controllerMap.put("/front-controller/v4/members/new-form", MemberFormControllerV4())
        controllerMap.put("/front-controller/v4/members/save", MemberSaveControllerV4())
        controllerMap.put("/front-controller/v4/members", MemberListControllerV4())
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        val requestURI = request.requestURI

        val controller = controllerMap[requestURI]

        if (controller == null) {
            response.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        val paramMap: MutableMap<String, String> = createParamMap(request)
        val model: MutableMap<String, Any> = hashMapOf()

        val viewName = controller.process(paramMap, model)

        val view = viewResolver(viewName) // 물리적 이름 (full path)

        view.render(model, request, response)
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