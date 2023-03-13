package hello.servelet.web.frontcontroller.v4.controller

import hello.servelet.domain.member.MemberRepository
import hello.servelet.web.frontcontroller.v4.ControllerV4

class MemberListControllerV4: ControllerV4 {

    private val memberRepository = MemberRepository.getInstance()

    override fun process(paramMap: MutableMap<String, String>, model: MutableMap<String, Any>): String {
        val members = memberRepository.findAll()

        model["members"] = members
        return "members"
    }
}