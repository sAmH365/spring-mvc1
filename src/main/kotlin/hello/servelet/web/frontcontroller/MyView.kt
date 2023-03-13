package hello.servelet.web.frontcontroller

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class MyView(
    private val viewPath: String
) {

    fun render(request: HttpServletRequest, response: HttpServletResponse) {
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }

    fun render(model: MutableMap<String, Any>, request: HttpServletRequest, response: HttpServletResponse) {
        modelToRequestAttribute(model, request)
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }

    private fun modelToRequestAttribute(
        model: MutableMap<String, Any>,
        request: HttpServletRequest
    ) {
        model.forEach { (key, value) ->
            request.setAttribute(key, value) // requst.setAttribute()에 담아놔야지 jsp에서 편하게 사용 가능, jsp 말고 다른 템플릿에서는 다른방식 사용
        }
    }
}