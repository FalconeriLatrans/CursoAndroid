/*1.	Criar um dicionário que represente os primeiros 5 números da “Loteria dos Sonhos”,
junto com seu significado. Para isso, o dicionário deverá ter como chave Integer e como valor uma String.
Números da Loteria dos Sonhos:
0 → Ovos
1 → Água
2 → Escopeta
3 → Cavalo
4 → Dentista
5 → Fogo

2.	Criar um dicionário que represente os apelidos que uso para chamar os meus amigos. Para isso, o dicionário deverá ter como chave String e como valor uma lista de Strings.

	João 	→ 	Juan, Fissura, Maromba
	Miguel → 	Night Watch, Bruce Wayne, Tampinha
	Maria 	→ 	Wonder Woman, Mary, Marilene
Lucas 	→ 	Lukinha, Jorge, George

Em seguida crie uma classe Exercício 1 com seu método main, imprimir no Main todas as chaves junto com seus valores associados.
Para isso, utilizar o foreach com as chaves.

 */
var lista = mapOf<Int,String>(0 to "Ovos", 1 to "Água", 2 to "Escopeta", 3 to "Cavalo", 4 to "Dentista", 5 to "Fogo")
var apelidos = mapOf<String,List<String>>(
    "João" to listOf("Juan", "Fissura", "Maromba"),
    "Miguel" to listOf("Night Watch", "Bruce Wayne", "Tampinha"),
    "Maria" to listOf("Wonder Woman", "Mary", "Marilene"),
    "Lucas" to listOf("Lukinha", "Jorge", "George")
)
fun main(){
    println("===== Lista 1 =====")
    lista.forEach{println(it)}
    println()
    println("===== Lista 2 =====")
    apelidos.forEach{println(it)}
}