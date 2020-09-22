open class Ingresso(var valor:Double = 0.0){
    open fun imprimeValor() {
        println(valor)
    }
}
open class VIP(valor:Double,var valorAdicional:Double):Ingresso(valor){
    override fun imprimeValor() {
        println(valor+valorAdicional)
    }
}
class Normal(valor:Double):Ingresso(valor){
    override fun imprimeValor() {
        super.imprimeValor()
        println("Ingresso Normal")
    }
}
class CamaroteInferior(valor:Double, valorAdicional: Double,var localizacao:String):VIP(valor,valorAdicional){
    fun imprimeLocalizacao(){
        println(localizacao)
    }
}
class CamaroteSuperior(valor:Double, var valorAdicionalSuperior:Double, valorAdicional:Double):VIP(valor,valorAdicional){
    override fun imprimeValor(){
        println(valor+valorAdicional+valorAdicionalSuperior)
    }
}
fun main(){
    var ingr01 = Ingresso(10.0)
    println("Digite 1 para ingresso normal ou 2 para VIP.")
    var resposta = readLine()
    if (resposta=="1") println("Ingresso normal.") else {
        println ("Ingresso VIP.")
        println("Digite 1 para camarote superior ou 2 para camarote inferior.")
        var resposta = readLine()
        if (resposta=="1") println("Ingresso VIP, camarote superior.") else println("Ingresso VIP, camarote inferior.")
    }
}
/*Exercício
1) Crie uma classe chamada Ingresso que possui um valor em reais e uma função imprimeValor().
● crie uma classe VIP, que herda Ingresso e possui um valor adicional. Crie
uma função que retorne o valor do ingresso VIP (com o adicional incluído).
● crie uma classe Normal, que herda Ingresso e possui uma função que
imprime: "Ingresso Normal".
● crie uma classe CamaroteInferior (que possui a localização do ingresso e
a função para acessar e imprimir esta localização) e uma classe
CamaroteSuperior, que é mais cara (possui valor adicional). Esta última
possui uma função para retornar o valor do ingresso. Ambas as classes
herdam a classe VIP.
Crie uma classe de Teste com a função main. Neste método:
● Crie um ingresso. Peça para o usuário digitar 1 para normal e 2 para VIP.
Conforme a escolha do usuário, diga se o ingresso é do tipo normal ou VIP.
Se for VIP, peça para ele digitar 1 para camarote superior ou 2 para
camarote inferior. Conforme a escolha do usuário, diga se que o VIP é
camarote superior ou inferior. Imprima o valor do ingresso. */