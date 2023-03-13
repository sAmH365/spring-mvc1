package hello.servelet.web.frontcontroller.v5

import hello.servelet.web.frontcontroller.MyView
import hello.servelet.web.frontcontroller.v3.controller.MemberFormControllerV3
import hello.servelet.web.frontcontroller.v3.controller.MemberListControllerV3
import hello.servelet.web.frontcontroller.v3.controller.MemberSaveControllerV3
import hello.servelet.web.frontcontroller.v4.controller.MemberFormControllerV4
import hello.servelet.web.frontcontroller.v4.controller.MemberListControllerV4
import hello.servelet.web.frontcontroller.v4.controller.MemberSaveControllerV4
import hello.servelet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter
import hello.servelet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.lang.IllegalArgumentException

@WebServlet(name = "FrontControllerServletV5", urlPatterns = ["/front-controller/v5/*"])
class FrontControllerServletV5: HttpServlet() {
    private val handlerMappingMap:MutableMap<String, Any> = hashMapOf()
    private val handlerAdapters:MutableList<MyHandlerAdapter> = arrayListOf()


    init {
        initHandlerMappingMap()
        initHandlerAdapters()
    }

    private fun initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", MemberFormControllerV3())
        handlerMappingMap.put("/front-controller/v5/v3/members/save", MemberSaveControllerV3())
        handlerMappingMap.put("/front-controller/v5/v3/members", MemberListControllerV3())

        // V4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", MemberFormControllerV4())
        handlerMappingMap.put("/front-controller/v5/v4/members/save", MemberSaveControllerV4())
        handlerMappingMap.put("/front-controller/v5/v4/members", MemberListControllerV4())
    }

    private fun initHandlerAdapters() {
        handlerAdapters.add(ControllerV3HandlerAdapter())
        handlerAdapters.add(ControllerV4HandlerAdapter())
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {

        val handler = getHandler(request)

        if (handler == null) {
            response.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        val adapter = getHandlerAdapters(handler)

        val mv = adapter.handle(request, response, handler)

        val viewName = mv.viewName // 논리적 이름
        val view = viewResolver(viewName) // 물리적 이름 (full path)

        view.render(mv.model, request, response)
    }

    private fun getHandler(request: HttpServletRequest): Any? {
        val requestURI = request.requestURI
        return handlerMappingMap[requestURI]
    }

    private fun getHandlerAdapters(handler: Any): MyHandlerAdapter {
        for (adapter in handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter
            }
        }

        throw IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler = $handler")
    }

    private fun viewResolver(viewName: String) = MyView("/WEB-INF/views/${viewName}.jsp")
}