fun main(){
    println("===== Exercício 01 =====")
    var P1 = Pessoa("Alberto",326541)
    var P2 = Pessoa("Alberto",326541)
    println(P1.equals(P2))
    println()
    println("===== Exercício 02 =====")
    var C1 = Coca(13,20.0)
    var C2 = Coca(13,20.0)
    println(C1 ==C2)
    println()
    println("===== Exercício 03 =====")
    var listaDeAlunos = mutableListOf<Aluno>()
    listaDeAlunos.add(Aluno("Alberto",123))
    listaDeAlunos.add(Aluno("Bernardo",124))
    listaDeAlunos.add(Aluno("Cesar",125))
    listaDeAlunos.add(Aluno("Diego",126))
    var aluno5 = Aluno("Edson",125)
    println(listaDeAlunos.contains(aluno5))
    println()
    println("===== Exercício 04 =====")
    var listaDeFuncionarios = mutableListOf<Funcionario>()
    listaDeFuncionarios.add(Funcionario("Alberto",123))
    listaDeFuncionarios.add(Funcionario("Bernardo",124))
    listaDeFuncionarios.add(Funcionario("Cesar",125))
    listaDeFuncionarios.add(Funcionario("Diego",126))
    var func5 = Funcionario("Edson",125)
    println(listaDeFuncionarios.contains(func5))
}
class Pessoa(var nome:String, var RG:Int){
    override fun equals(other: Any?): Boolean {
        if (other is Pessoa){
            return RG == other.RG
        } else return false
    }
}

class Coca(var tamanho:Int, var preco:Double){
    override fun equals(other: Any?): Boolean {
        if (other is Coca){
            return tamanho == other.tamanho
        } else return false
    }
}
class Aluno(var nome:String, var numeroDeAluno:Int){
    override fun equals(other: Any?): Boolean {
        if (other is Aluno){
            return numeroDeAluno == other.numeroDeAluno
        } else return false
    }
}
class Funcionario(var nome:String, var numeroDeRegistro:Int){
    override fun equals(other: Any?): Boolean {
        super.equals(other)
        if (other is Funcionario) {
            return numeroDeRegistro == other.numeroDeRegistro
        } else return false
    }
}

/*
Exercício 1:

1.	Criar a classe Pessoa que tenha como pripriedades nome (String) e RG (Integer).
2.	Na classe Main, criar duas pessoas com o mesmo número de RG.
3.	Usando equals, comparar se a primeira pessoa criada é igual à segunda. Qual é o resultado?
4.	Sobrescrever o equals da classe Pessoa para que compare apenas o número de RG.
5.	Refazer a comparação utilizando o equals. Qual é o resultado? O que mudou? Por quê?

Exercício 2:

1.	Criar a classe Coca que tenha como propriedades um tamanho (Integer) e preço (Double).
2.	Na classe Main,dvd criar duas Cocas com o mesmo tamanho.
3.	Usando equals, comparar se a primeira Coca criada é igual à segunda. Qual é o resultado?
4.	Sobrescrever o equals da classe Coca para que compare apenas o tamanho.
5.	Refazer a comparação utilizando o equals. Qual é o resultado? O que mudou? Por quê?

Exercício 3:

1.	Criar uma classe Aluno que tenha como propriedades um nome (String) e numeroDeAluno (Integer).
2.	Na classe Main, criar uma lista de alunos e adicionar quatro alunos novos.
3.	Criar um aluno novo com um número de aluno contido na lista e perguntar se ele está na lista (utilizar a função contains).
4.	Sobrescrever o equals da classe Aluno para que compare apenas o número de aluno.
5.	Perguntar novamente se o aluno novo está na lista. Qual é o resultado? O que mudou? Por quê?

Exercício 4:

1.	Criar uma classe Funcionário que tenha como propriedades  um nome (String) e numeroDeRegistro (Integer).
2.	Criar na classe Main uma lista de funcionários e adicionar quatro funcionários novos.
3.	Criar um funcionário novo com um número de registro contido na lista e perguntar se ele está na lista (utilizar a função contains)
4.	Sobrescrever o equals da classe Funcionário para que compare apenas o número de registro.
5.	Perguntar novamente se o funcionário novo está na lista. Qual é o resultado? O que mudou? Por quê?
*/