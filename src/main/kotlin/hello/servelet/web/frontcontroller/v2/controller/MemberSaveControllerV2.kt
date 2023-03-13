package hello.servelet.web.frontcontroller.v2.controller

import hello.servelet.domain.member.Member
import hello.servelet.domain.member.MemberRepository
import hello.servelet.web.frontcontroller.MyView
import hello.servelet.web.frontcontroller.v2.ControllerV2
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class MemberSaveControllerV2: ControllerV2 {

    private val memberRepository = MemberRepository.getInstance()

    override fun process(request: HttpServletRequest, response: HttpServletResponse): MyView {

        val username = request.getParameter("username")
        val age = Integer.parseInt(request.getParameter("age"))

        val member = Member(username, age)
        memberRepository.save(member)

        //Model에 데이터를 보관한다.
        request.setAttribute("member", member)

        return MyView("/WEB-INF/views/save-result.jsp")
    }
}