package hello.servelet.web.springmvc.v1

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView


//@Component
//@RequestMapping// 스프링 3.0 이상부터 클래스 레벨에 @RequestMapping이 있어도 컨트롤러로 인식 못함
@Controller
class SpringMemberFormControllerV1 {

    init {
        println("SpringMemberFormControllerV1.init!!")
    }

    @RequestMapping("/springmvc/v1/members/new-form")
    fun process(): ModelAndView {
        println("SpringMemberFormControllerV1.process()")
        return ModelAndView("new-form")
    }


}