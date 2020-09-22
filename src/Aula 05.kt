fun main(){
    var dog = Cachorro()
    var que = Pato()
    dog.nascer()
    que.nascer()
}

open class Animal(){
    open fun nascer(){
        print("Eu nasci")
    }
}
class Cachorro(): Animal(){
    override fun nascer() {
        super.nascer()
        println(" de uma gestação.")
    }
}
class Pato(): Animal(){
    override fun nascer() {
        super.nascer()
        println(" de um ovo.")
    }
}