package hello.servelet.web.frontcontroller.v3.controller

import hello.servelet.domain.member.MemberRepository
import hello.servelet.web.frontcontroller.ModelView
import hello.servelet.web.frontcontroller.MyView
import hello.servelet.web.frontcontroller.v3.ControllerV3

class MemberListControllerV3: ControllerV3 {

    private val memberRepository = MemberRepository.getInstance()

    override fun process(paramMap: MutableMap<String, String>): ModelView {
        val members = memberRepository.findAll()

        val mv = ModelView("members")
        mv.model["members"] = members
        return mv
    }
}