import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter

abstract class Pessoa(var nome:String, var sobrenome:String){
    override fun toString(): String {
        return "$nome $sobrenome"
    }
}

class Aluno(
    nome:String,
    sobrenome:String,
    var codigoDeAluno:Int
):Pessoa(nome,sobrenome){
    override fun equals(other: Any?): Boolean {
        return other==codigoDeAluno
    }
    override fun hashCode(): Int {
        return codigoDeAluno
    }
    override fun toString(): String {
        //"0000X:   nome sobrenome" escrito em ciano
        return "\u001B[36m" + "$codigoDeAluno: ".padStart(7, '0') + super.toString().padStart(18) + "\u001B[0m"
    }
}

open class Professor(
    nome:String,
    sobrenome:String,
    private var dataDeRegistro:LocalDateTime,
    var codigoDeProfessor:Int
):Pessoa(nome,sobrenome){
    fun setDataDeRegistro(data:LocalDateTime){
        dataDeRegistro = data
        println(this.toString() + ": Data de registro alterada para " + dataDeRegistro.format(DateTimeFormatter.ISO_DATE)) //YYYY-MM-DD
    }
    fun tempoDeCasa():String{
        val period = Period.between(dataDeRegistro.toLocalDate(),LocalDateTime.now().toLocalDate())
        var saida1 = this.toString()
        saida1 += " está registrado a "
        var saida2: String = "" + period.years + " anos, " +  period.months + " meses e " + period.days + " dias."
        //mensagem personalizada de aniversário
        if (period.years>0&&period.months==0&&period.days==0){
            saida2 = "exatos " + period.years + " anos. PARABÉNS!!"
        }
        return saida1 + saida2
    }
    override fun equals(other: Any?): Boolean {
        return other==codigoDeProfessor
    }

    override fun hashCode(): Int {
        return codigoDeProfessor
    }
    override fun toString(): String {
        //"0000X:   nome sobrenome" escrito em azul
        return "\u001B[34m" + "$codigoDeProfessor: ".padStart(7, '0') + super.toString().padStart(18) + "\u001B[0m"
    }
    open fun descCompleta():String{
        val period = Period.between(dataDeRegistro.toLocalDate(),LocalDateTime.now().toLocalDate())
        return this.toString() + ", desde " + dataDeRegistro.format(DateTimeFormatter.ISO_DATE) + "(" + "${period.years*12 + period.months}".padStart(4, '0') + " meses)"
    }
}

class ProfessorTitular(
    nome: String,
    sobrenome: String,
    dataDeRegistro: LocalDateTime,
    codigoDeProfessor: Int,
    var especialidade:String = "nenhuma"
): Professor(nome, sobrenome, dataDeRegistro, codigoDeProfessor){
    override fun toString(): String {
        return super.toString() + "\u001B[34m" + " (titular)" + "\u001B[0m"
    }

    override fun descCompleta(): String {
        return super.descCompleta() + " Especialidade: " + especialidade
    }
}

class ProfessorAdjunto(
    nome: String,
    sobrenome: String,
    dataDeRegistro: LocalDateTime,
    codigoDeProfessor: Int,
    var horasDeMonitoria:Int = 0
): Professor(nome, sobrenome, dataDeRegistro, codigoDeProfessor){
    override fun toString(): String {
        return super.toString() + "\u001B[34m" + " (adjunto)" + "\u001B[0m"
    }

    override fun descCompleta(): String {
        return super.descCompleta() + " Horas de monitoria: " + "$horasDeMonitoria".padStart(3, '0')
    }
}

class Curso(
    var nomeDoCurso:String,
    var codigoDoCurso:Int,
    var qtdMaxAlunos:Int,
){
    var professorTitular: Professor? = null
    var professorAdjunto: Professor? = null
    var listaDeAlunos:MutableSet<Aluno> = mutableSetOf()

//construtor alternativo caso o Digital House Manager já tiver os professores definidos
    constructor (
        nomeDoCurso: String,
        codigoCurso: Int,
        qtdMaxAlunos: Int,
        professorTitular: Professor?,
        professorAdjunto: Professor?,
        listaDeAlunos:MutableSet<Aluno> = mutableSetOf()
    ):this(nomeDoCurso, codigoCurso, qtdMaxAlunos){
        this.professorTitular = professorTitular
        this.professorAdjunto = professorAdjunto
    }

    override fun equals(other: Any?): Boolean {
        return other==codigoDoCurso
    }

    override fun hashCode(): Int {
        return codigoDoCurso
    }

    override fun toString(): String {
        //"0000X:       Descrição" escrito em amarelo
        return "\u001B[33m" + "$codigoDoCurso: ".padStart(7, '0') + "$nomeDoCurso".padStart(18) + "\u001B[0m"
    }
    fun descCompleta():String{
        var saida = this.toString() + "\n"
        saida += "Alunos/Capacidade: " + "${listaDeAlunos.size}".padStart(2, '0') + "/" + "$qtdMaxAlunos".padStart(2, '0') + "\n"
        saida += "Professor titular: " + professorTitular.toString() + "\n"
        saida += "Professor adjunto: " + professorAdjunto.toString() + "\n"
        return saida
    }
    //Função verifica se existem vagas disponíveis, e caso exista ela matricula o aluno. Caso contrário, retorna uma mensagem de capacidade máxima e um valor false.
    //A função completa de matrícula está na classe DigitalHouseManager, onde faz outras checagens e preenche outras listas.
    fun adicionarUmAluno(umAluno: Aluno): Boolean {
        try {
            if (qtdMaxAlunos > listaDeAlunos.size) {
                listaDeAlunos.add(umAluno)
                return true
            } else {
                print("Esse curso já atingiu a capacidade máxima. ")
                return false
            }
        } catch (e: Exception){
            print("ERRO: ")
            return false
        }
    }
    //Função apenas verifica se o aluno está na lista e o exclui.
    //A função completa de exclusão de alunos está na classe DigitalHouseManager, onde faz outras checagens e o exclui de outras demais listas.
    fun excluirAluno(umAluno: Aluno?){
        if (umAluno!=null) {
            listaDeAlunos.remove(umAluno)
        }
    }
}

class Matricula(
    var aluno: Aluno,
    var curso: Curso,
    var dataDeMatricula: LocalDateTime = LocalDateTime.now()
){
    override fun toString(): String {
        return "${aluno} matriculado no curso $curso em ${dataDeMatricula.format(DateTimeFormatter.ISO_DATE)}"//YYYY-MM-DD
    }
}

class DigitalHouseManager(
    var listaDeAlunos: MutableSet<Aluno> = mutableSetOf(),
    var listadeProfessores: MutableSet<Professor> = mutableSetOf(),
    var listadeCursos: MutableSet<Curso> = mutableSetOf(),
    var listadeMatriculas: MutableSet<Matricula> = mutableSetOf()
){
    fun registrarCurso(nome: String, codigoCurso: Int, quantidadeMaximaDeAlunos: Int){
        try {
            val curso = Curso(nome,codigoCurso,quantidadeMaximaDeAlunos)
            if (listadeCursos.contains(curso)){
                println("Código já existente. Por favor, tente novamente.")
            } else {
                listadeCursos.add(curso)
                println(curso.toString() + " registrado com sucesso.")
            }
        } catch(e: Exception) {
            println("Não foi possível registrar o curso solicitado.")
        }
    }
    fun registrarCurso(nome: String, codigoCurso: Int, quantidadeMaximaDeAlunos: Int,codproftit:Int, codprofadj: Int){
        try {
            val proftit = listadeProfessores.find{it.codigoDeProfessor==codproftit&&(it is ProfessorTitular)}
            val profadj = listadeProfessores.find{it.codigoDeProfessor==codprofadj&&(it is ProfessorAdjunto)}
            val curso = Curso(nome,codigoCurso,quantidadeMaximaDeAlunos,proftit, profadj)
            if (listadeCursos.contains(curso)){
                println("Código já existente. Por favor, tente novamente.")
            } else {
                listadeCursos.add(curso)
                println(curso.descCompleta() + "Registrado com sucesso.")
            }
        } catch(e: Exception) {
            println("Não foi possível registrar o curso solicitado.")
        }
    }
    //Função apenas remove o curso da lista deste ProjectManager. O histórico de matrículas ainda permanece.
    fun removerCurso(codigoCurso: Int){
        try{
            val curso = listadeCursos.find{it.codigoDoCurso==codigoCurso}
            if (curso != null){
                listadeCursos.remove(curso)
                println("$curso removido com sucesso")
            } else {
                println("O curso código $codigoCurso não pode ser removido.")
            }
        } catch(e: Exception){
            println("ERRO: O curso não pode ser removido.")
        }
    }
    //Função remove o curso e as matrículas respectivas.
    fun excluirCurso(codigoCurso: Int){
        try{
            val curso = listadeCursos.find{it.codigoDoCurso==codigoCurso}
            if (curso != null){
                listadeCursos.remove(curso)
                listadeMatriculas.removeIf { it.curso==curso }
                println("$curso excluído com sucesso")
            } else {
                println("O curso código $codigoCurso não pode ser excluído.")
            }
        } catch(e: Exception){
            println("ERRO: O curso não pode ser removido.")
        }
    }
    fun registrarProfessorAdjunto(nome: String, sobrenome: String, codigoProfessor: Int, quantidadeDeHoras: Int){
        val novoprof = ProfessorAdjunto(nome,sobrenome, LocalDateTime.now(),codigoProfessor,quantidadeDeHoras)
        if (listadeProfessores.contains(novoprof).not()){
            listadeProfessores.add(novoprof)
            println(novoprof.toString() + " registrado com sucesso.")
        } else {
            println("Código já existente. Por favor, tente novamente.")
        }
    }
    fun registrarProfessorTitular(nome: String, sobrenome: String , codigoProfessor: Int, especialidade: String ){
        val novoprof = ProfessorTitular(nome,sobrenome, LocalDateTime.now(),codigoProfessor,especialidade)
        if (listadeProfessores.contains(novoprof).not()) {
            listadeProfessores.add(novoprof)
            println(novoprof.toString() + " registrado com sucesso.")
        } else {
            println("Código já existente. Por favor, tente novamente.")
        }
    }
    // Remove o professor da lista, desfaz a associação do professor aos cursos que ele ministrava e alerta o usuário quais cursos ficaram órfãos.
    fun excluirProfessor(codigoProfessor: Int){
        try {
            val professor = listadeProfessores.find{it.codigoDeProfessor==codigoProfessor}
            if (professor!=null){
                listadeProfessores.remove(professor)
                println("Professor $professor removido com sucesso")
                listadeCursos.forEach {
                    if (it.professorAdjunto==professor) {
                        it.professorAdjunto = null
                        println("${it} agora está sem professor adjunto.")
                    }
                    if (it.professorTitular==professor) {
                        it.professorTitular = null
                        println("${it} agora está sem professor titular.")
                    }
                }
            } else {
                println("Professor $codigoProfessor não encontrado. Por favor, tente novamente.")
            }
        } catch(e: Exception){
            println("Erro: O professor código $codigoProfessor não pode ser removido.")
        }
    }
    fun matricularAluno(nome: String , sobrenome: String , codigoAluno: Int){
        val novoaluno = Aluno(nome,sobrenome,codigoAluno)
        if (listaDeAlunos.contains(novoaluno)){
            println("Código já existente. Por favor, tente novamente.")
        } else {
            listaDeAlunos.add(novoaluno)
            println(novoaluno.toString()+" cadastrado com sucesso.")
        }
    }
    fun matricularAluno(codigoAluno: Int,  codigoCurso: Int){
        val curso = listadeCursos.find { it.codigoDoCurso ==codigoCurso}
        val aluno= listaDeAlunos.find { it.codigoDeAluno ==codigoAluno}
        if (curso==null){
            println("Curso código $codigoCurso não encontrado")
        }
        if (aluno==null){
            println("Aluno código $codigoAluno não encontrado.")
        }
        if (aluno!=null&&curso!=null) {
            if (curso.listaDeAlunos.contains(aluno).not()) {
                if (curso.adicionarUmAluno(aluno)) {
                    val matricula = Matricula(aluno, curso, LocalDateTime.now())
                    listadeMatriculas.add(matricula)
                    println(aluno.toString() + " matriculado no curso " + curso.toString() + " com sucesso.")
                } else {
                    println("Não foi possível realizar a matrícula.")
                }
            } else {
                println(aluno.toString() + " já matriculado no curso " + curso.toString() + ".")
            }
        } else {
            println("Não foi possível realizar a matrícula.")
        }
    }
    fun excluirAluno(codigoAluno: Int){
        val aluno = listaDeAlunos.find{it.codigoDeAluno==codigoAluno}
        if (aluno!=null) {
            listaDeAlunos.remove(aluno)
            listadeCursos.forEach { it.excluirAluno(aluno) }
            listadeMatriculas.removeAll { it.aluno==aluno }
            println("${aluno} removido com sucesso.")
        }
    }
    fun alocarProfessores(codigoCurso: Int, codigoProfessorTitular: Int, codigoProfessorAdjunto: Int){
        var sucesso = true
        val curso = listadeCursos.find {it.codigoDoCurso == codigoCurso}
        val proftit = listadeProfessores.find {it.codigoDeProfessor == codigoProfessorTitular}
        val profadj = listadeProfessores.find {it.codigoDeProfessor == codigoProfessorAdjunto}
        if (curso==null){
            println("Curso código $codigoCurso não encontrado")
            sucesso = false
        }
        if (proftit==null){
            println("Professor titular código $codigoProfessorTitular não encontrado.")
            sucesso = false
        } else if ((proftit is ProfessorTitular).not()){
            println(proftit.toString() + " não está cadastrado como titular.")
            sucesso = false
        }
        if (profadj==null){
            println("Professor adjunto código $codigoProfessorAdjunto não encontrado.")
            sucesso = false
        } else if ((profadj is ProfessorAdjunto).not()){
            println(profadj.toString() + " não está cadastrado como adjunto.")
            sucesso = false
        }
        if (sucesso.not()) {
            println("Não foi possível fazer a alocação dos professores. Por favor, tente novamente.")
        } else if (curso!=null){
            curso.professorTitular = proftit
            curso.professorAdjunto = profadj
            println(proftit.toString() + " e " + profadj.toString() + " alocados ao curso " + curso.toString() + " com sucesso.")
        }
    }
//Resumo das informações gerais
    override fun toString(): String {
        var saida = "==== DIGITAL HOUSE MANAGER ====\n"
        saida += "${listadeCursos.size}".padStart(3, '0') + " cursos cadastrados\n"
        saida += "${listadeProfessores.size}".padStart(3, '0') + " professores cadastrados\n"
        saida += "${listaDeAlunos.size}".padStart(3, '0') + " alunos cadastrados\n"
        saida += "${listadeMatriculas.size}".padStart(3, '0') + " matrículas realizadas."
        return saida
    }
}
fun main(){
    val manager = DigitalHouseManager()
    println("===== CADASTRANDO NOVOS ALUNOS =====")
    manager.matricularAluno("Alberto", "Souza", 1)
    manager.matricularAluno("Bernardo", "Silva", 2)
    manager.matricularAluno("Carlos", "Medeiros", 3)
    manager.matricularAluno("Dalton", "Ferreira", 4)
    manager.matricularAluno("Edson", "Campos", 5)
    manager.matricularAluno("Fagner", "Lima", 6)
    manager.matricularAluno("Gustavo", "Farias", 7)
    manager.matricularAluno("Hercules", "Pereira", 7)
    println("===== CADASTRANDO NOVOS CURSOS =====")
    manager.registrarCurso("Python Avançado",1,2)
    manager.registrarCurso("Java Iniciante",2,5)
    manager.registrarCurso("Amarrar Sapatos",2,10)
    manager.registrarCurso("Vender um Marea",3,15)
    println("===== CADASTRANDO NOVOS PROFESSORES =====")
    manager.registrarProfessorTitular("Zélia", "Almeida", 1,"Kotlin")
    manager.registrarProfessorTitular("Yngrid", "Ferraz", 2,"Web")
    manager.registrarProfessorTitular("Xavier", "Charles", 3,"Risoto de camarão")
    manager.registrarProfessorTitular("Wagner", "Montoia", 4,"Artes Marciais")
    manager.registrarProfessorAdjunto("Valéria", "Menezes",4,10)
    manager.registrarProfessorAdjunto("Ulisses", "Parreira",5,15)
    manager.registrarProfessorAdjunto("Tiago", "Lobato",6,1)
    manager.registrarProfessorAdjunto("Sara", "Cardoso",7,0)
    println("===== TEMPO DE CASA =====")
    manager.listadeProfessores.find { it.codigoDeProfessor==1 }?.setDataDeRegistro(LocalDateTime.of(2015,2,20,10,30))
    manager.listadeProfessores.find { it.codigoDeProfessor==2 }?.setDataDeRegistro(LocalDateTime.of(2018,LocalDateTime.now().month,LocalDateTime.now().dayOfMonth,10,0))
    println(manager.listadeProfessores.find { it.codigoDeProfessor==1 }?.tempoDeCasa())
    println(manager.listadeProfessores.find { it.codigoDeProfessor==2 }?.tempoDeCasa())
    println("===== MATRICULANDO ALUNOS =====")
    manager.matricularAluno(1,1)
    manager.matricularAluno(1,1)
    manager.matricularAluno(2,1)
    manager.matricularAluno(3,1)
    manager.matricularAluno(3,2)
    manager.matricularAluno(4,2)
    manager.matricularAluno(5,2)
    manager.matricularAluno(6,3)
    manager.matricularAluno(7,3)
    manager.matricularAluno(15,2)
    manager.matricularAluno(5,20)
        println("===== ALOCANDO PROFESSORES =====")
    manager.alocarProfessores(1,1,5)
    manager.alocarProfessores(1,2,5)
    manager.alocarProfessores(2,1,6)
    manager.alocarProfessores(2,7,3)
    manager.alocarProfessores(1,67,7)
    manager.alocarProfessores(1,3,33)
    manager.alocarProfessores(34,4,6)
    println("===== CADASTRANDO NOVOS CURSOS =====")//construtor alternativo
    manager.registrarCurso("Dança do Ventre",6,30,78,3)
    manager.registrarCurso("Hacker Iniciante",7,30,4,7)
    manager.registrarCurso("Hacker Avançado",7,30,1,7)
    println("===== EXCLUINDO CURSOS =====")
        println("lista de cursos original:")
    manager.listadeCursos.forEach{println(it)}
    println()
    manager.removerCurso(4) // apenas remove o curso da lista de cursos
    manager.excluirCurso(3) // remove o curso e as matrículas respectivas ao curso
    println("lista de cursos após remover/excluír:")
    manager.listadeCursos.forEach{println(it)}
    println()
    println("===== EXCLUINDO PROFESSORES =====")
    manager.excluirProfessor(3)
    manager.excluirProfessor(6)
    println("===== EXCLUINDO ALUNOS =====")
    manager.excluirAluno(3)
    println("===== LISTA DE MATRÍCULAS =====")
    manager.listadeMatriculas.forEach{println(it)}
    println("===== LISTA DE CURSOS =====")
    manager.listadeCursos.forEach{println(it.descCompleta())}
    println("===== LISTA DE PROFESSORES =====")
    manager.listadeProfessores.forEach{println(it.descCompleta())}
    println("===== LISTA DE ALUNOS =====")
    manager.listaDeAlunos.forEach{println(it)}
    println(manager)
}
