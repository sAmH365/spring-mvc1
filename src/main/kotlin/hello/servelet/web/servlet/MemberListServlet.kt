package hello.servelet.web.servlet

import hello.servelet.domain.member.Member
import hello.servelet.domain.member.MemberRepository
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "MemberListServlet", urlPatterns = ["/servlet/members"])
class MemberListServlet: HttpServlet() {

    private val memberRepository = MemberRepository.getInstance()

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        response.contentType = "text/html"
        response.characterEncoding = "utf-8"

        val members = memberRepository.findAll()

        val w = response.writer
        w.write("<html>");
        w.write("<head>");
        w.write("    <meta charset=\"UTF-8\">");
        w.write("    <title>Title</title>");
        w.write("</head>");
        w.write("<body>");
        w.write("<a href=\"/index.html\">메인</a>");
        w.write("<table>");
        w.write("    <thead>");
        w.write("    <th>id</th>");
        w.write("    <th>username</th>");
        w.write("    <th>age</th>");
        w.write("    </thead>");
        w.write("    <tbody>");
/*
        w.write("<tr>")
        w.write("<td>1</td>")
        w.write("<td>userA</td>")
        w.write("<td>10</td>")
        w.write("</tr>")
*/

        for (member: Member in members) {
            w.write("    <tr>");
            w.write("        <td>" + member.id + "</td>");
            w.write("        <td>" + member.username + "</td>");
            w.write("        <td>" + member.age + "</td>");
            w.write("    </tr>");
        }
        w.write("    </tbody>");
        w.write("</table>");
        w.write("</body>");
        w.write("</html>");
    }
}