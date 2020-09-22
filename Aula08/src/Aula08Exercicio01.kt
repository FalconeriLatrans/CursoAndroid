
/*Exercício 1
A Digital House decidiu desenvolver um sistema de cadastro de alunos com seus
respectivos cursos, professores responsáveis e turmas, para esse sistema foram levantados
os seguintes requisitos:
• A matéria dada em cada aula possui um nome;
• A aula possui uma matéria, um horário de início e término;
• O aluno possui um RA (Registro Acadêmico), nome, sobrenome.
• O aluno pode assistir as aulas e fazer as lições de casa;
• O curso possui um nome, uma lista de aulas, uma lista de alunos e um professor
responsável;
• O professor possui um nome e um RD (Registro Docente);
• O professor por ser um Docente tem a obrigatoriedade de dar aulas e fazer a chamada
dos alunos;
• A turma deverá conter um nome e um curso associado.
*/

interface Pessoa{
    var nome:String
    var sobrenome:String
}
class Aluno(override var nome:String,override var sobrenome:String, var RA:Int, var curso:Curso):Pessoa{
    fun assistirAula(){}
    fun fazerLicaoDeCasa(){}
}
class Professor(override var nome:String,override var sobrenome:String, var RD:Int):Pessoa{
    fun darAula(aula:Aula){
        curso.aulas.
    }
    fun fazerChamada(){}
}
class Turma(var nome:String, var alunos: MutableList<Aluno> = mutableListOf(),var curso:Curso)
class Curso(var nome:String, var alunos: MutableList<Aluno> = mutableListOf(), var aulas: MutableList<Aula> = mutableListOf(), var professor:Professor){
    var aulasFeitas: MutableList<Boolean> = mutableListOf()
    fun aulaAtual():Aula{

    }
    fun realizarAula(aulas(aulaAtual)){

    }
}
class Aula(var materia:Materia, var horaInicio:String, var horaTermino:String){
    var realizada:Boolean = false
}
class Materia(var nome:String)



