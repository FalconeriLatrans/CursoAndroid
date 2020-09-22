/*Exercício 2

Crie uma classe chamada Fatura que possa ser utilizado por uma loja de
suprimentos de informática para representar uma fatura de itens vendidos na loja.
Uma fatura deve incluir as seguintes informações como atributos:

● Uma lista de itens cada Item possui:

• o número do item faturado;
• a descrição do item;
• a quantidade comprada do item
• o preço unitário do item.

Sua classe Item deve conter um construtor que inicialize os quatro atributos. Se a
quantidade não for positiva, ela deve ser configurada como 0. Se o preço por item não
for positivo ele deve ser configurado como 0.0.
Além disso, na classe Fatura um método chamado getTotalFatura que calcula o valor da
fatura (isso é, multiplicar a quantidade pelo preço de cada item) e depois retorna o valor
como um double.
Escreva uma classe Main de teste que demonstra as capacidades da classe Fatura.
*/

class Fatura(var listaDeItens: MutableList<Item> = mutableListOf()){
    var totalFatura = 0.0
    fun meuTotalFatura():Double{
        totalFatura = 0.0
        listaDeItens.forEach(){
            totalFatura += it.preco * it.qtdComprada
        }
        return totalFatura
    }
    fun addItem(){}

}
class Item(var numero:Int = 0, var descricao:String = "", var qtdComprada:Int = 0, var preco:Double = 0.0){
    init{
        if (qtdComprada < 0){
            qtdComprada = 0
        }
        if (preco < 0.0){
            preco = 0.0
        }
    }
    override fun toString():String{
        return "numero: $numero, Descrição: $descricao, Qtd: $qtdComprada, Preço unitário: $preco"
    }
}
fun main(){
    var teclado = Item(1,"teclado ABNT", 1,75.00)
    var mouse = Item(2,"mouse sem fio", 1,55.00)
    var monitor = Item(3,"monitor 24 pol", 2,900.00)
    var gabinete = Item(4,"Gamer modelo 2", 1,6000.00)
    var compra01 = Fatura(mutableListOf(teclado,mouse,monitor,gabinete))
    compra01.listaDeItens.forEach(){
        println(it.toString())
    }
    println(compra01.meuTotalFatura())
}