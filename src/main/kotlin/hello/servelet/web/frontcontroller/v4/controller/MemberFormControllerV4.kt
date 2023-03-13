package hello.servelet.web.frontcontroller.v4.controller

import hello.servelet.web.frontcontroller.v4.ControllerV4

class MemberFormControllerV4: ControllerV4 {

    override fun process(paramMap: MutableMap<String, String>, model: MutableMap<String, Any>): String {
        return "new-form"
    }
}