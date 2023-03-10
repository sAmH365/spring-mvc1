package hello.servelet.web.servlet

import hello.servelet.domain.member.Member
import hello.servelet.domain.member.MemberRepository
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "MemberSaveServlet", urlPatterns = ["/servlet/members/save"])
class MemberSaveServlet:HttpServlet() {

    private val memberRepository = MemberRepository.getInstance()

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        val username = request.getParameter("username")
        val age = Integer.parseInt(request.getParameter("age"))

        val member = Member(username, age)
        memberRepository.save(member)

        response.contentType = "text/html"
        response.characterEncoding = "utf-8"

        val w = response.writer
        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" + "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "    <li>id="+member.id+"</li>\n" +
                "    <li>username="+member.username+"</li>\n" +
                " <li>age="+member.age+"</li>\n" + "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" + "</body>\n" +
                "</html>");
    }
}