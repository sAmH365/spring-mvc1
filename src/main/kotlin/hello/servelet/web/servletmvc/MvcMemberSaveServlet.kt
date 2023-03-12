package hello.servelet.web.servletmvc

import hello.servelet.domain.member.Member
import hello.servelet.domain.member.MemberRepository
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = ["/servlet-mvc/members/save"])
class MvcMemberSaveServlet:HttpServlet() {

    private val memberRepository = MemberRepository.getInstance()

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        val username = request.getParameter("username")
        val age = Integer.parseInt(request.getParameter("age"))

        val member = Member(username, age)
        memberRepository.save(member)

        //Model에 데이터를 보관한다.
        request.setAttribute("member", member)

        val viewPath = "/WEB-INF/view/save-request.jsp"
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }
}