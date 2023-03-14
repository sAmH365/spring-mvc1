package hello.servelet.web.springmvc.v2

import hello.servelet.domain.member.Member
import hello.servelet.domain.member.MemberRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import java.lang.IllegalArgumentException

@Controller
@RequestMapping("/springmvc/v2/members")
class SpringMemberControllerV2 {

    private val memberRepository = MemberRepository.getInstance()

    @RequestMapping("/new-form")
    fun newFrom(): ModelAndView {
        println("SpringMemberFormControllerV1.process()")
        return ModelAndView("new-form")
    }

    @RequestMapping()
    fun members(request: HttpServletRequest, response: HttpServletResponse): ModelAndView {
        val members = memberRepository.findAll()

        val mv = ModelAndView("members")
        mv.addObject("members", members)
        return mv
    }

    @RequestMapping("/save")
    fun save(request: HttpServletRequest, response: HttpServletResponse): ModelAndView {
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