package hello.servelet.web.frontcontroller.v3.controller

import hello.servelet.web.frontcontroller.ModelView
import hello.servelet.web.frontcontroller.v3.ControllerV3

class MemberFormControllerV3: ControllerV3 {

    override fun process(paramMap: MutableMap<String, String>): ModelView {
        return ModelView("new-form")
    }
}