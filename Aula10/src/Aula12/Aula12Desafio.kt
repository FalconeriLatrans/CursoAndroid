package Aula12

import java.time.LocalDate

/*EXERCÍCIO INTEGRADOR

Até agora nós vimos programação orientada a objetos com Kotlin (com e sem objetos), herança, classes abstratas, interfaces e exceções.
Vamos utilizar alguns destes conceitos para desenvolver o um sistema de administração de estoque de uma Livraria/Biblioteca.
Para isto, temos os seguintes requisitos:
1.	Uma classe Livraria/Biblioteca contendo nome e data de criação;
2.	Uma classe Livro com código, título, autor, ano de lançamento, preço de venda, preço de aluguel (por dia) e estado atual (disponível, alugado ou vendido);
3.	Uma classe Cliente com nome, rg e histórico de aluguéis e compras;
4.	Uma classe Funcionário com nome, rg e histórico dos aluguéis e vendas;

5.	A biblioteca/livraria deve ter os seguintes métodos:
a.	Cadastrar Livro - Deve incluir um novo livro com todas as informações necessárias.
b.	Cadastrar Coleção - Deve incluir uma nova coleção com todas as informações necessárias, incluindo a lista de livros.
c.	Consultar Livro/Coleção por código ou por nome - Exibe as informações sobre o livro/coleção com o código digitado.
    Se não existir nenhum livro/coleção com esse código, exibir a mensagem “Livro/coleção não encontrado”
d.	Alugar Livro/Coleção - Altere o estado do livro/coleção para alugado
e.	Efetuar venda (por código) - Se o usuário selecionar esta opção, ele deve informar o código do livro ou da coleção que será vendido.
f.	Verificar estoque - O sistema deve retornar o número de livros disponíveis, alugados e vendidos e também somar o valor dos livros vendidos.

Enviar o link do GitHub para o email dos professores (apenas Código)
cnascimento@digitalhouse.com
emisina@digitalhouse.com
*/

class Livraria(
        var nome: String,
        var dataDeCriacao: LocalDate,
        var indisponiveis: MutableList<Livro> = mutableListOf(),
        var disponiveis: MutableList<Livro> = mutableListOf(),
        var alugados: MutableList<Livro> = mutableListOf(),
        var vendidos: MutableList<Livro> = mutableListOf(),
        var todos: MutableList<Livro> = mutableListOf()
){
    var itemAtual = 1
    fun cadastrarLivro(livro:Livro){
        livro.codigo = itemAtual
        todos.add(livro)
        when (livro.estadoAtual) {
            "disponivel" -> {disponiveis.add(livro)}
            "alugado" -> {alugados.add(livro)}
            "vendido" -> {vendidos.add(livro)}
            else -> {indisponiveis.add(livro)}
        }
        println("Livro ${livro.titulo} cadastrado com sucesso.")
        itemAtual ++
    }
    fun cadastrarColecao(colecao:Colecao){
        colecao.codigo = itemAtual
        todos.add(colecao)
        when (colecao.estadoAtual) {
            "disponivel" -> {disponiveis.add(colecao)}
            "alugado" -> {alugados.add(colecao)}
            "vendido" -> {vendidos.add(colecao)}
            else -> {indisponiveis.add(colecao)}
        }
        println("Coleção ${colecao.titulo} cadastrada com sucesso.")
        itemAtual ++
    }
    fun consultar(ref:Any){
        if (ref is Int){
            for (a in todos){
                if (a.codigo == ref) {
                    println("${a.codigo}:${a.titulo} está ${a.estadoAtual}.")
                }
            }
        } else if (ref is String){
            for (a in todos){
                if (a.titulo == ref) {
                    println("${a.codigo}:${a.titulo} está ${a.estadoAtual}.")
                }
            }
        }
    }
    fun alugarLivro(livro:Livro, cliente:Cliente, funcionario: Funcionario?){
        disponiveis.remove(livro)
        alugados.add(livro)
        livro.estadoAtual = "alugado"
        val novamovimentacao = Movimentacao("Aluguel",LocalDate.now())
        cliente.historico.add(novamovimentacao)
        if (funcionario != null) {
            funcionario.historico.add(novamovimentacao)
        }
        var louc = "Livro"
        if (livro is Colecao) {louc = "Coleção"}
        println("$louc alugado para ${cliente.nome}.")
    }
    fun venderLivro(livro:Livro, cliente:Cliente, funcionario: Funcionario?){
        disponiveis.remove(livro)
        vendidos.add(livro)
        livro.estadoAtual = "vendido"
        val novamovimentacao = Movimentacao("Venda",LocalDate.now())
        cliente.historico.add(novamovimentacao)
        if (funcionario != null) {
            funcionario.historico.add(novamovimentacao)
        }
        var louc = "Livro ${livro.titulo} vendido"
        if (livro is Colecao) {louc = "Coleção ${livro.titulo} vendida"}
        println("$louc para ${cliente.nome}.")
    }
    fun verificarEstoque(){
        println("==== LIVROS VENDIDOS ====")
        vendidos.forEach{println("${it.codigo}: ${it.titulo}")}
        println("Valor total das vendas: ${vendidos.sumOf{it.precoDeVenda}}")
        println("==== LIVROS ALUGADOS ====")
        alugados.forEach{println("${it.codigo}: ${it.titulo}")}
        println("==== LIVROS DISPONÍVEIS ====")
        disponiveis.forEach{println("${it.codigo}: ${it.titulo}")}
    }

}
open class Livro(
        open var titulo:String,
        open var autores:MutableSet<String> = mutableSetOf("Autor desconhecido"),
        open var anoDeLancamento:Int,
        open var precoDeVenda:Double,
        open var precoDeAluguel:Double,
        open var estadoAtual:String = "indisponível"
){
    open var codigo:Int = 0
}
class Colecao(
        override var titulo:String = "",
        override var autores:MutableSet<String> = mutableSetOf("Autor desconhecido"),
        override var anoDeLancamento:Int = 0,
        override var precoDeVenda:Double = 0.0,
        override var precoDeAluguel:Double = 0.0,
        override var estadoAtual:String = "indisponível",
        var listaDeTitulos:MutableList<Livro>
):Livro(titulo, autores,anoDeLancamento, precoDeVenda, precoDeAluguel, estadoAtual){
    override var codigo:Int = 0
    init{
        if ("Autor desconhecido" in autores){
            for (a in listaDeTitulos){
                for (autor in a.autores){
                    autores.add(autor)
                }
            }
        }
        if (anoDeLancamento == 0){
            for (a in listaDeTitulos){
                if (anoDeLancamento < a.anoDeLancamento){
                    anoDeLancamento = a.anoDeLancamento
                }
            }
        }
        if (precoDeAluguel == 0.0) {
            for (a in listaDeTitulos) {
                precoDeAluguel += a.precoDeAluguel
            }
        }
        if (precoDeVenda == 0.0){
            for (a in listaDeTitulos){
                precoDeVenda += a.precoDeVenda
            }
        }
        for (a in listaDeTitulos){
            if (a.estadoAtual == "indisponível"){
                estadoAtual = "indisponível"
            }
        }
    }
}

class Movimentacao(
        var tipo:String,
        var data:LocalDate
){
    override fun toString(): String {
        return ("$data: $tipo")
    }
}
abstract class Pessoa(
        var nome:String,
        var RG:String,
        var historico: MutableList<Movimentacao> = mutableListOf()
){
    open fun checar(){
        historico.forEach{println(it)}
    }
}
class Cliente(
        nome: String,
        RG: String,
        historico: MutableList<Movimentacao> = mutableListOf()
):Pessoa(nome,RG, historico){
    override fun checar(){
        println("Movimentações feitas pelo cliente $nome RG:$RG:")
        super.checar()
    }
}
class Funcionario(
        nome: String,
        RG:String,
        historico: MutableList<Movimentacao> = mutableListOf()
):Pessoa(nome,RG, historico){
    override fun checar(){
        println("Movimentações coordenadas pelo funcionário $nome RG:$RG:")
        super.checar()
    }
}
fun main(){
    val livraria01 = Livraria("Livraria Bolso Vazio Mente Cheia",LocalDate.now())
    val livro01 = Livro("O Livro Sem História", mutableSetOf("João Sem nome", "Maria sem sobrenome"), 2002,30.0,3.0,"disponível")
    val livro02 = Livro("A História Sem Livro", mutableSetOf("João Sem nome"), 2012,35.0,2.0,"alugado")
    val livro03 = Livro("A Coleção de Muitos Livros 01", mutableSetOf("Lombeiro Montato"), 2002,30.0,3.0,"disponível")
    val livro04 = Livro("A Coleção de Muitos Livros 02", mutableSetOf("Lombeiro Montato"), 2002,35.0,3.0,"disponível")
    val colecao01 = Colecao("A Coleção de Muitos Livros",anoDeLancamento = 2005,listaDeTitulos = mutableListOf(livro03,livro04), estadoAtual = "disponível")
    val funcionario01 = Funcionario("Barnabé","15.123.223-6")
    val cliente01 = Cliente("Chico","42.678.114-4")
    println()
    println("Testes de cadastro:")
    livraria01.cadastrarLivro(livro01)
    livraria01.cadastrarLivro(livro02)
    livraria01.cadastrarColecao(colecao01)
    println()
    println("Testes de consulta:")
    livraria01.consultar("O Livro Sem História")
    livraria01.consultar(2)
    livraria01.consultar("A Coleção de Muitos Livros")
    println()
    println("Testes de movimentações:")
    livraria01.alugarLivro(livro01,cliente01,funcionario01)
    livraria01.venderLivro(colecao01,cliente01, null)
    println()
    println("Testes de checagem:")
    livraria01.verificarEstoque()
    funcionario01.checar()
    cliente01.checar()
}