fun main(){
    aula05exercicio01(4)
    aula05exercicio02(10,"M",0)
    aula05exercicio03(mutableListOf(1,3,5))
    aula05exercicio04()
    aula05exercicio05()
}

fun aula05exercicio01(numero:Int){
    println("========= AULA 05 EXERCÍCIO 01 =========")
    println("O fatorial de $numero é:")
    println(fatorial(numero))
}
fun fatorial(numero: Int):Int{
    if (numero <= 1){
        print("1 = ")
        return 1
    } else {
        print("$numero * ")
        return fatorial(numero - 1)*numero
    }
}
fun aula05exercicio02(idade:Int, sexo:String, contribuicao:Int){
    println("========= AULA 05 EXERCÍCIO 02 =========")
    var pessoa1 = Aposentado(idade,sexo,contribuicao)
    pessoa1.podeAposentar()
}
class Aposentado(idade:Int, sexo: String, contribuicao: Int){
    var idade = idade
    var sexo = sexo
    var contribuicao = contribuicao
    var aposOK = false
    init {
        if (contribuicao >= 30){
            if ((sexo == "M") && (idade>=65)) {
                aposOK = true
            } else if (idade>=60){
                aposOK = true
            }
        }
    }
    fun podeAposentar(){
        if (aposOK) println("verdadeiro") else println ("false")
    }
}
fun aula05exercicio03(lista: MutableList<Int>){
    println("========= AULA 05 EXERCÍCIO 03 =========")
    println(lista)
    println(lista.reduce(Int::times))
}
fun aula05exercicio04(){
    println("========= AULA 05 EXERCÍCIO 04 =========")
    var cliente01 = Cliente("João", "Silva")
    var cliente02 = Cliente("Pedro", "Souza")
    var conta001 = Conta(1,1000.0,cliente01)
    var conta002 = Conta(2,2000.0,cliente02)
    conta001.saque(100.0)
    conta001.deposito(300.0)
    conta002.saque(500.0)
    conta002.deposito(200.0)
}
class Cliente(var nome:String, var sobrenome:String = "Não informado"){
}
open class Conta(var numeroDaConta: Int = 0, var saldo: Double, var titular: Cliente){
    fun deposito(valor:Double){
        saldo += valor
        println("Operação depósito:")
        println("O saldo atualizado agora é R$:${saldo}.")
    }
    fun saque(valor:Double){
        if (saldo >= valor) {
            saldo -= valor
            println("Operação saque:")
            println("O saldo atualizado agora é R$:${saldo}.")
        } else {
            println("Saldo insuficiente.")
        }
    }
}
fun aula05exercicio05(){
    println("========= AULA 05 EXERCÍCIO 05 =========")
    var jogadorA = JogadorDeFutebol("Batata", 100,20,1,0)
    var jogadorB = JogadorDeFutebol("Treiz Dedo", 40,80,9,4)
    var treino01 = SessaoDeTreinamento(4)
    treino01.treinarA(jogadorA)
    treino01.treinarA(jogadorB)
}
class JogadorDeFutebol(var nome: String = "",var energia:Int = 0,var alegria:Int = 0,var gols:Int = 0, var experiencia:Int = 0){
    fun fazerGol(){
        energia-=5
        alegria+=10
        gols+=1
        println("${nome}: GOOOOL!")
    }
    fun correr() {
        energia -= 10
        println("${nome}: Cansei")
    }
}
class SessaoDeTreinamento(var experiencia:Int =0){
    fun treinarA (jogador: JogadorDeFutebol) {
        println("Experiência inicial do jogador ${jogador.nome} é ${jogador.experiencia}.")
        jogador.correr()
        jogador.fazerGol()
        jogador.correr()
        jogador.experiencia += 1
        println("Experiência final do jogador ${jogador.nome} é ${jogador.experiencia}.")
    }
}
