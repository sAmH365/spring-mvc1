package hello.servelet.web.frontcontroller.v1.controller

import hello.servelet.domain.member.MemberRepository
import hello.servelet.web.frontcontroller.v1.ControllerV1
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class MemberListControllerV1: ControllerV1 {

    private val memberRepository = MemberRepository.getInstance()

    override fun process(request: HttpServletRequest, response: HttpServletResponse) {
        val members = memberRepository.findAll()

        request.setAttribute("members", members)

        val viewPath = "/WEB-INF/views/members.jsp"

        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }
}