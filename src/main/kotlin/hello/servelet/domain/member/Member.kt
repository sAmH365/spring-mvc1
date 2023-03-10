package hello.servelet.domain.member

class Member {

    var id = 0L
    var username = ""
    var age = 0

    constructor()

    constructor(username: String, age: Int) {
        this.username = username
        this.age = age
    }
}