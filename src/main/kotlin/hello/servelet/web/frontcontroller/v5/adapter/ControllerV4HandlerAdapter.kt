package hello.servelet.web.frontcontroller.v5.adapter

import hello.servelet.web.frontcontroller.ModelView
import hello.servelet.web.frontcontroller.v4.ControllerV4
import hello.servelet.web.frontcontroller.v5.MyHandlerAdapter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class ControllerV4HandlerAdapter: MyHandlerAdapter {
    override fun supports(handler: Any): Boolean {
        return (handler is ControllerV4)
    }

    override fun handle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): ModelView {
        val controller = handler as ControllerV4

        val paraMap = createParamMap(request)
        val model = hashMapOf<String, Any>()

        val viewName = controller.process(paraMap, model)

        val mv = ModelView(viewName)
        mv.model = model

        return mv
    }

    private fun createParamMap(request: HttpServletRequest): MutableMap<String, String> {
        val paramMap: MutableMap<String, String> = hashMapOf()
        request.parameterNames.asIterator()
            .forEachRemaining {paramName ->
                paramMap[paramName] = request.getParameter(paramName)
            }

        return paramMap
    }
}