/*
Exercício 3:

Criar uma nova classe chamada Prova. Definir e implementar o seguinte método:
public void somaTotal(Set<Integer> conjuntoDeInteiros)

O método deve percorrer o conjunto de inteiros, ir acumulando o total da soma dos valores e, no final, imprimir na
tela o valor da soma total.
Em seguida crie uma classe Exercício 3 com seu método main, imprimir no Main todas as chaves junto com seus valores associados.
Para isso, utilizar o foreach com as chaves.
*/
class Prova() {
    fun somaTotal(conjuntoDeInteiros:Set<Int>){
        //var total = 0
        //conjuntoDeInteiros.forEach{total+=it}
        //println(total)
        println(conjuntoDeInteiros.sum())
    }
}
fun main(){
    var teste = Prova()
    var provas = setOf(1,2,5,6,78,5,6)
    println("==== Notas ====")
    provas.forEach{print("$it ")}
    println()
    println("==== Soma ====")
    teste.somaTotal(provas)
}