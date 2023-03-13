package hello.servelet.web.frontcontroller.v5.adapter

import hello.servelet.web.frontcontroller.ModelView
import hello.servelet.web.frontcontroller.v3.ControllerV3
import hello.servelet.web.frontcontroller.v5.MyHandlerAdapter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class ControllerV3HandlerAdapter: MyHandlerAdapter {
    override fun supports(handler: Any): Boolean {
        return (handler is ControllerV3)
    }

    override fun handle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): ModelView {
        val controller = handler as ControllerV3

        val paramMap = createParamMap(request)
        val mv = controller.process(paramMap)

        return mv
    }

    private fun createParamMap(request: HttpServletRequest): MutableMap<String, String> {
        val paramMap: MutableMap<String, String> = hashMapOf()
        request.parameterNames.asIterator()
            .forEachRemaining { paramName ->
                paramMap[paramName] = request.getParameter(paramName)
            }
        return paramMap
    }
}