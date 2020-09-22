/*
Exercício 2:
3. Definir e inicializar uma nova lista vazia. Adicionar os seguintes elementos à lista vazia: 1,5,5,6,7,8,8,8.

Imprimir o resultado na tela.

4. Definir e inicializar um novo conjunto vazio. Adicionar os seguintes elementos ao conjunto vazio: 1,5,5,6,7,8,8,8.
Imprimir o resultado na tela. Qual é a diferença entre este exercício e o anterior?

Em seguida crie uma classe Exercício 2 com seu método main, imprimir no Main todas as chaves junto com seus valores associados.
Para isso, utilizar o foreach com as chaves ou o método toString().
*/

var listaEx2_2 = mutableListOf<Int>()
var listaEx2_3 = mutableSetOf<Int>()

fun main(){
    println("===== Exercício 02 - 03 =====")
    listaEx2_2.addAll(listOf(1,5,5,6,7,8,8,8))
    listaEx2_2.forEach{print(" $it")}
    println()

    println("===== Exercício 02 - 04 =====")
    listaEx2_3.addAll(listOf(1,5,5,6,7,8,8,8))
    listaEx2_3.forEach{print(" $it")}
    println()
}