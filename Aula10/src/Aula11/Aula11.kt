package Aula11

import java.lang.Exception
import kotlin.math.absoluteValue

fun main(){
    val lista = MutableList(30, {MutableList(30, { it * 2 })})
    var hourglasses:MutableList<Int> = MutableList((lista[1].size-3)*(lista.size-3),{0})
//    try{
//        println(lista[4])
//    } catch(exception: Exception){
//        println("Erro")
//    }
    //println(lista.sorted())
//    println(lista.count { it==1 })
//    for (a in 0..lista.size){
//
//    }

    println(lista.last())
}