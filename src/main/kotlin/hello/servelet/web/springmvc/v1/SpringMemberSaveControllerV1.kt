package hello.servelet.web.springmvc.v1

import hello.servelet.domain.member.Member
import hello.servelet.domain.member.MemberRepository
import hello.servelet.web.frontcontroller.ModelView
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import java.lang.IllegalArgumentException

@Controller
class SpringMemberSaveControllerV1 {

    private val memberRepository= MemberRepository.getInstance()

    @RequestMapping("/springmvc/v1/members/save")
    fun process(request: HttpServletRequest, response: HttpServletResponse): ModelAndView {
        val username = request.getParameter("username")
        val age = Integer.parseInt(request.getParameter("age"))

        val member = Member(
            username ?: throw IllegalArgumentException("username must be not null")
            , age
        )

        memberRepository.save(member)

        val mv = ModelAndView("save-result")
//        mv.model["member"] = member
        mv.addObject("member", member)
        return mv
    }
}