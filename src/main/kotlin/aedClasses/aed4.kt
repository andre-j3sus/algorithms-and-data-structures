/**
AULA 4 - 6/4/2021
Análise da complexidade assintótica(temporal)
 - iterativo
 - recursivo : - método das substituiçoes recursivas
               - teorema mestre (algoritmos do tipo dividir para conquistar)

Notações:
 O - limite assintótico superior
 Omega - limite assintótico inferior
 Theta - junção das duas notações O e Omega
 */

fun deleteSpaces(str:String):String{
    val n = str.length
    var newStr = ""
    for (i in 0 until n){
        if(str[i] != ' ') newStr += str[i]
    }
    return newStr
}