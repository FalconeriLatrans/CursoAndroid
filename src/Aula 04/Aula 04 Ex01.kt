package `Aula 04`

fun main() {
    var num = 1
    var num2 = 6
    var num3 = 3
    var lista = arrayListOf(num,num2,num3)
    var maior = 0
    lista.forEach(){
        if (it > maior){
            maior = it
        }
    }
    println(maior)
}