package hello.servelet.web.frontcontroller.v3

import hello.servelet.web.frontcontroller.ModelView

interface ControllerV3 {

    fun process(paramMap: MutableMap<String, String>): ModelView
}