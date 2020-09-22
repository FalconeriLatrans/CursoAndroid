interface IRecebivel{
    fun totalizarReceita():Double
}
class ItemVenda(var produto:String, var quantidade:Int, var valor:Double):IRecebivel{
    fun itemVenda(produto:String, quantidade:Int, valor:Double){
    }
    override fun totalizarReceita(): Double {
        return 0.0
        TODO("Not yet implemented")
    }
    override fun toString():String {
        return "Nada"
    }

}
class Servico(var descricao:String, var horas:Int, var precoHora:Double):IRecebivel{
    fun servico(descricao:String, horas:Int, precoHora:Double){
    }
    override fun totalizarReceita(): Double {
        return 0.0
        TODO("Not yet implemented")
    }
    override fun toString():String{
        return "Nada"
    }
}
abstract class RegistroRecebimentos(){
    abstract fun RegistroRecebimentos()
    abstract fun adicionarRecebimento(r:IRecebivel):Void
    abstract fun apresentarRecebimentos():Void
}

/* Interfaces Kotlin
Criar uma interface Imprimível que contém duas propriedades nome e
tipo de documento, ambas são do tipo String. Em seguida crie a assinatura da
função imprimir().
Agora crie três classes, elas serão nossos documentos, cada um deles
precisará implementar a interface Imprimível, sobrescrevendo suas
propriedades e funções. Mas cada documento deve saber se imprimir:
FOTO -> Na função deve printar, “Eu sou uma selfie” utilize do Strings
templates para concatenar o nome e o tipo da Impressão
DOCUMENTO -> Na função deve printar, “Eu sou um documento word”,
utilize do Strings templates para concatenar o nome e o tipo da Impressão
CONTRATO -> Na função deve printar, “Sou um contrato muito legal”,
utilize do Strings templates para concatenar o nome e o tipo da Impressão
Em seguida crie uma classe chamada Impressora, nesta classe terá uma
propriedade chamada listaimprimivel, essa propriedade é do tipo
mutableListOf, que vai carregar uma lista do tipo Imprimivel.
Crie uma função que pega um Imprimível e adiciona na listaimprimivel,
para isso a função recebe como parâmetro uma propriedade do tipo Imprimivel,
assim conseguirá adicionar esse imprimível na lista, através do .add().
Crie uma função que imprima tudo que está na listaimprimivel, nessa
função pode-se utilizar do for para percorrer a lista e imprimir todos os aquivos.
Segue o exemplo:
for (tipoDaLista: in nomeDaLista){
//execute o bloco
}
Por fim crie um arquivo kotlin que contenha a função Main, dentro dela
crie um objeto de cada classe, sendo Documento, Foto, Contrato e Impressora.
Agora utilizando a impressora adicione cada documento na impressão e
imprima todos.

 */

interface Imprimivel{
    fun imprimir(){
        print("Eu sou")
    }
}
class FOTO(): Imprimivel{
    override fun imprimir() {
        super.imprimir()
        println(" uma selfie")
    }
}
class DOCUMENTO(): Imprimivel{
    override fun imprimir() {
        super.imprimir()
        println(" um documento word")
    }
}
class CONTRATO(): Imprimivel{
    override fun imprimir() {
        println("Sou um contrato muito legal")
    }
}
class Impressora(){
    var listaimprimivel = mutableListOf<Imprimivel>()
    fun adicionar(docto: Imprimivel){
        listaimprimivel?.add(docto)
    }
    fun imprimir(){
        println(listaimprimivel)
        listaimprimivel?.forEach{
            it.imprimir()
        }
    }
}
fun main(){
    var documento = DOCUMENTO()
    var foto = FOTO()
    var contrato = CONTRATO()
    var impressora = Impressora()
    impressora.adicionar(documento)
    impressora.adicionar(foto)
    impressora.adicionar(contrato)
    impressora.imprimir()
}