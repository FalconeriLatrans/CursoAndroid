package Aula11

import java.lang.Exception

fun main(){
    var lista = mutableListOf(1,2,3)
    try{
        println(lista[4])
    } catch(exception: Exception){
        println("Erro")
    }
}