package hello.servelet.web.frontcontroller.v4.controller

import hello.servelet.domain.member.Member
import hello.servelet.domain.member.MemberRepository
import hello.servelet.web.frontcontroller.ModelView
import hello.servelet.web.frontcontroller.v4.ControllerV4
import java.lang.IllegalArgumentException

class MemberSaveControllerV4: ControllerV4 {

    private val memberRepository = MemberRepository.getInstance()

    override fun process(paramMap: MutableMap<String, String>, model: MutableMap<String, Any>): String {
        val username = paramMap["username"]
        val age = Integer.parseInt(paramMap["age"])

        val member = Member(
            username ?: throw IllegalArgumentException("username must be not null")
            , age
        )
        memberRepository.save(member)

        model["member"] = member
        return "save-result"
    }
}