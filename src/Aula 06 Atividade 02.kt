class Cliente01(var numero:Int, var sobrenome:String, var RG:String, var CPF:String)
open class Conta01(var cliente:Cliente01, var saldo:Double=0.0){
    open fun depositar(valor:Double){
        saldo+=valor
        println("Depósito efetuado.")
    }
    open fun sacar(valor:Double){
        if (saldo>valor) {
            saldo -= valor
            println("Saque efetuado.")
        } else {
            println("Saldo insuficiente.")
        }
    }
    open fun consultar(){
        println("O saldo disponível é de ${saldo}.")
    }
}
class Poupanca(cliente:Cliente01, saldo:Double):Conta01(cliente,saldo){
    var juros = 0.05
    var acumulado = 0.0
    fun recolherJuros(){
        saldo-=acumulado
        println("Saque de $acumulado efetuado.")
    }
}
class Corrente(cliente:Cliente01, saldo:Double):Conta01(cliente,saldo){
    var limiteCheque = 500
    var chequeUtilizado = 0.0
    fun depositarCheque(valor:Double, bancoEmissor:String, data:String){
        var valor = valor
        if (chequeUtilizado > 0.0){
            if (valor >= chequeUtilizado) {
                valor -= chequeUtilizado
                chequeUtilizado = 0.0
            } else {
                chequeUtilizado -= valor
                valor = 0.0
            }
        }
        saldo+=valor
        println("Depósito efetuado.")
        consultar()
    }
    override fun depositar(valor:Double){
        var valor = valor
        if (chequeUtilizado > 0.0){
            if (valor >= chequeUtilizado) {
                valor -= chequeUtilizado
                chequeUtilizado = 0.0
            } else {
                chequeUtilizado -= valor
                valor = 0.0
            }
        }
        saldo+=valor
        println("Depósito efetuado.")
        consultar()
    }
    override fun sacar(valor:Double){
        if (saldo>valor) {
            saldo -= valor
            println("Saque efetuado.")
        } else if (saldo+limiteCheque-chequeUtilizado>valor) {
            chequeUtilizado += (valor - saldo)
            saldo = 0.0
            println("Saque efetuado. ")
        } else {
            println("Saldo insuficiente.")
        }
        consultar()
    }

    override fun consultar() {
        super.consultar()
        println("O Cheque Especial disponível é ${limiteCheque-chequeUtilizado} e o valor utilizado é de $chequeUtilizado.")
    }
}
fun main(){
    var cliente = Cliente01(1,"Souza","09.222","9827")
    var conta01 = Corrente(cliente,500.0)
    conta01.sacar(600.0)
    conta01.depositar(50.0)
    conta01.depositar(400.0)
}



/*
O Banco Santander solicita a modelagem de um novo sistema. Esse sistema será usado para registrar as contas e os clientes do banco.

Clientes: os clientes serão identificados por um número de cliente, um sobrenome, um número de RG e um CPF.

Contas: as contas do banco permitem fazer depósitos, sacar dinheiro e consultar o saldo. Cada conta está associada a um cliente.

Extensão:

Conta poupança: além do saldo, as contas poupança têm uma taxa de juros. Neste tipo de conta, é possível realizar três operações:
○ Depositar dinheiro: o cliente pode depositar a quantia de dinheiro que quiser.
○ Sacar dinheiro: o cliente pode sacar dinheiro desde que não supere seu saldo.
○ Recolher juros: o cliente pode recolher os juros mensais aplicados pela sua conta poupança.

Conta corrente: além do saldo, as contas correntes têm um limite autorizado de cheque especial. Neste tipo de conta, é possível realizar três operações:
○ Depositar dinheiro: o cliente pode depositar a quantia de dinheiro que quiser.
○ Depositar cheques: o cliente pode depositar cheques. Um cheque tem um valor, um banco emissor e uma data de pagamento.
○ Sacar dinheiro: o cliente pode sacar dinheiro e, caso não tenha saldo suficiente, usar seu cheque especial.


Este exercício já é familiar pois na aula de Orientação a Objetos desenvolvemos o UML deste exercício.
Agora com base no UML desenvolvido anteriormente implemente todo o diagrama em Java contendo todos os atributos e métodos das classes de acordo com o que pede o enunciado.

https://wordpad.cc/bj4kU2lH
 */