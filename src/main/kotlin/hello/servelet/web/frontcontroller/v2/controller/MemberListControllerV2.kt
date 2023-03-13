package hello.servelet.web.frontcontroller.v2.controller

import hello.servelet.domain.member.MemberRepository
import hello.servelet.web.frontcontroller.MyView
import hello.servelet.web.frontcontroller.v2.ControllerV2
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class MemberListControllerV2: ControllerV2 {

    private val memberRepository = MemberRepository.getInstance()

    override fun process(request: HttpServletRequest, response: HttpServletResponse): MyView {
        val members = memberRepository.findAll()
        request.setAttribute("members", members)
        return MyView("/WEB-INF/views/members.jsp")
    }
}