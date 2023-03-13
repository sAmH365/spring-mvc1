package hello.servelet.web.frontcontroller.v1.controller

import hello.servelet.domain.member.Member
import hello.servelet.domain.member.MemberRepository
import hello.servelet.web.frontcontroller.v1.ControllerV1
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class MemberSaveControllerV1:ControllerV1 {
    private val memberRepository = MemberRepository.getInstance()

    override fun process(request: HttpServletRequest, response: HttpServletResponse) {
        val username = request.getParameter("username")
        val age = Integer.parseInt(request.getParameter("age"))

        val member = Member(username, age)
        memberRepository.save(member)

        //Model에 데이터를 보관한다.
        request.setAttribute("member", member)

        val viewPath = "/WEB-INF/views/save-result.jsp"
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }
}