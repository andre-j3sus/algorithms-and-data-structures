package aedClasses
//Metodo 1 Fibonacci
//Complexidade de tempo O(2^n -> exponencial)
//Complexidade em espaço é O(n)
fun fibonacci1(n:Int):Int = if (n<=1) n else fibonacci1(n-1) + fibonacci1(n-2)


/*
Método 2 -> Programação dinamica - tecnica de memorização
Complexidade em tempo é O(n)
Complexidade em espaço é O(n)
 */
fun fibonacci2(n:Int):Int{
    val f = IntArray(n+2) //para gerir o espaço extra no caso do 0 (guardar f0 e f1)
    f[0]=0
    f[1]=1

    var i:Int = 2
    while (i<=n){
        f[i] = f[i-1] + f[i-2]
        i++
    }
    return f[n]
}

/*
TPC -> Metodo 3
Complexidade em tempo é O(n)
Complexidade em espaço é O(1) -> Constante
*/
fun fibonacci3(n:Int):Int{
    val f = IntArray(2)
    f[0]=0
    f[1]=1

    var i:Int = 2
    while (i<=n){
        val z = f[0] + f[1]
        f[0] = f[1]
        f[1] = z
        i++
    }
    return f[1]
}

/*-------------------------------------------------------------------------------------------------------------------*/

/*
Calculo da potencia a^n a>0 e n>=0
Complexidade em tempo é O(n)
Complexidade em espaço é O(n)
 */
fun potencia1(a:Int, n:Int):Int = if(n==0)1 else a*potencia1(a, n-1)


/*
Calculo da potencia a^n a>0 e n>=0
Complexidade em tempo é O(log2(n))
Complexidade em espaço é O(log2(n))
 */
fun potencia2(a:Int, n:Int):Int{
    if (n==0) return 1
    val z = potencia2(a, n/2)
    return if(n%2==0) z*z else z*z*a
}

/*-------------------------------------------------------------------------------------------------------------------*/

data class Triple(val left:Int, val sum:Double, val r:Int)

fun subArrayMax(array:DoubleArray, left: Int, right:Int):Triple{
    var l = left
    var r = left-1
    var bestSum = 0.0
    var actualSum:Double

    for (i in left..right){
        actualSum = 0.0
        for (j in i..right){
            actualSum += array[j]
            if(bestSum < actualSum){
                bestSum = actualSum
                r = j
                l = i
            }
        }
    }
    return Triple(l, bestSum, r)
}

fun subArrayMaxKadane(array:DoubleArray, left: Int, right:Int):Triple{
    var l = left
    var l2 = left
    var r = left-1
    var bestSum = 0.0
    var actualSum:Double = 0.0

    for (i in left..right){
        actualSum += array[i]
        if (actualSum > bestSum){
            bestSum = actualSum
            r = i
            l = l2
        }
        else if(actualSum < 0.0){
            l2 = if(i == right) i else i + 1
            actualSum = 0.0
        }

    }
    return Triple(l, bestSum, r)
}



fun main() {
    //println(fibonacci3(60)) //1820529360

    val testArray = doubleArrayOf(-5.0, 2.0, 3.0, -13.0, -4.0, 3.0, 1.0, -4.0)


    println(subArrayMax(testArray, 0, 4))
    println(subArrayMaxKadane(testArray, 0, 4))
}