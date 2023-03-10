package hello.servelet.domain.member

import org.junit.jupiter.api.AfterEach
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class MemberRepositoryTest {

    val memberRepository = MemberRepository.getInstance()

    @AfterEach
    fun afterEach() {
        memberRepository.clearStore()
    }

    @Test
    fun save() {
        // given
        val member = Member("kwon", 23)

        // when
        val savedMember = memberRepository.save(member)
        val findMember = memberRepository.findById(savedMember.id)

        // then
        assertThat(savedMember).`as`("저장 == 찾기").isEqualTo(findMember)
        assertThat(findMember.age).isEqualTo(23)
    }

    @Test
    fun findAll() {
        // given
        val member1 = Member("kwon", 23)
        val member2 = Member("kim", 25)
        val member3 = Member("Lee", 22)

        // when
        memberRepository.save(member1)
        memberRepository.save(member2)
        memberRepository.save(member3)

        val findAllMembers = memberRepository.findAll()

        // then
        assertThat(findAllMembers.size).isEqualTo(3)
        assertThat(findAllMembers).contains(member1, member2, member3)
    }
}