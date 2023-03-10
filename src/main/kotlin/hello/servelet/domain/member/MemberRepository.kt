package hello.servelet.domain.member

import java.util.concurrent.ConcurrentHashMap

/**
 * 동시성 문제가 고려외더 있지 않음,
 * 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
class MemberRepository {

    companion object {
         var store: MutableMap<Long, Member> = HashMap()
        private var sequence = 0L

        private val instance: MemberRepository = MemberRepository()

        fun getInstance(): MemberRepository {
            return this.instance
        }
    }

    private constructor()

    fun save(member: Member): Member {
        member.id = ++sequence
        store[member.id] = member
        return member
    }

    fun findById(memberId: Long): Member {
        return  store[memberId]!!
    }

    fun findAll(): MutableList<Member> {
        val values = store.values
        val members = mutableListOf<Member>()

        values.forEach {
            members.add(it)
        }

        return members
    }

    fun clearStore() {
        store.clear()
    }
}