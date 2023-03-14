package hello.servelet.web.springmvc.v1

import hello.servelet.domain.member.MemberRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class SpringMemberListControllerV1 {

    private val memberRepository= MemberRepository.getInstance()

    @RequestMapping("/springmvc/v1/members")
    fun process(request: HttpServletRequest, response: HttpServletResponse): ModelAndView {
        val members = memberRepository.findAll()

        val mv = ModelAndView("members")
        mv.addObject("members", members)
        return mv
    }
}