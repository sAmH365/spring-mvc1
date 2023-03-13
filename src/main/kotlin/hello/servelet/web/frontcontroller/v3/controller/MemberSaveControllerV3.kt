package hello.servelet.web.frontcontroller.v3.controller

import hello.servelet.domain.member.Member
import hello.servelet.domain.member.MemberRepository
import hello.servelet.web.frontcontroller.ModelView
import hello.servelet.web.frontcontroller.v3.ControllerV3
import java.lang.IllegalArgumentException

class MemberSaveControllerV3: ControllerV3{

    private val memberRepository = MemberRepository.getInstance()

    override fun process(paramMap: MutableMap<String, String>): ModelView {
        val username = paramMap["username"]
        val age = Integer.parseInt(paramMap["age"])

        val member = Member(
            username ?: throw IllegalArgumentException("username must be not null")
            , age
        )

        memberRepository.save(member)

        val mv = ModelView("save-result")
        mv.model["member"] = member
        return mv
    }
}