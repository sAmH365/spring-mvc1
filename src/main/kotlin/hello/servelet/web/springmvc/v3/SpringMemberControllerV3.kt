package hello.servelet.web.springmvc.v3

import hello.servelet.domain.member.Member
import hello.servelet.domain.member.MemberRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import java.lang.IllegalArgumentException

@Controller
@RequestMapping("/springmvc/v3/members")
class SpringMemberControllerV3 {
    private val memberRepository = MemberRepository.getInstance()

//    @RequestMapping("/new-form", method = [RequestMethod.GET])
    @GetMapping("/new-form")
    fun newFrom(): String {
        return "new-form"
//        println("SpringMemberFormControllerV1.process()")
//        return ModelAndView("new-form")
    }

//    @RequestMapping(method = [RequestMethod.GET])
    @GetMapping
    fun members(model: Model): String {
        val members = memberRepository.findAll()
        model.addAttribute("members", members)
        return "members"
    }

//    @RequestMapping("/save", method = [RequestMethod.POST])
    @PostMapping("/save")
    fun save(
        @RequestParam("username") username: String,
        @RequestParam("age") age: Int,
        model: Model
    ): String {

        val member = Member(username , age)

        memberRepository.save(member)

        model.addAttribute("member", member)
        return "save-result"
    }
}