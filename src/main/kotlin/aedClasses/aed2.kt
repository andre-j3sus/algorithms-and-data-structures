/*
Algoritmos de comparação
 - comparações -> elementares(insertion, selection, bubble, merge) e não elementares(quick, heap)
 - não comparações -> radix e counting

 */

/**
 * Insertion Sort -> inserir ordenadamente
 * n = right - left + 1
 * Melhor caso: já estar ordenado de modo crescente
 *      Omega(n)
 *
 * Pior caso : ordenado de modo descrescente
 *      O(n^2)
 *
 * Caso médio: quadrática
 */
fun insertionSort(a:IntArray, left:Int, right:Int){
    var value:Int
    var curIdx:Int
    for (i in left+1..right){
        value = a[i]
        curIdx = i
        while(curIdx > left && value < a[curIdx-1]){
            a[curIdx] = a[curIdx-1]
            curIdx--
        }
        a[curIdx] = value
    }
}

/**
 * Selection Sort -> selecionar o menor elemento e cada iteração
 * n = right - left + 1
 * Complexcidade temporal: teta(n^2) -> teta se for o melhor e pior caso
 * Independentemente da disttribuição dos dados, realizamos as mesmas iterações
 * Não é estável
 * TPC -> Tranformar selection num algoritmo estável
 */
fun selectionSort(a:IntArray, left:Int, right:Int){
    var min:Int
    for (j in left until right){ // os últimos dois já estão organizados
        min = j
        for(i in j+1..right){
            if (a[i] < a[min]) min = i
        }
        exch(a, j, min)
    }
    println(a.asList())
}

fun exch(a:IntArray, i:Int, j:Int){
    val temp = a[i]
    a[i] = a[j]
    a[j] = temp
}

fun selectionSortStable(a:IntArray, left:Int, right:Int){
    var min:Int
    for (j in left until right){ // os últimos dois já estão organizados
        min = j
        for(i in j+1..right){
            if (a[i] < a[min]) min = i
        }
        while (min > j){
            exch(a, min, min-1)
        }
    }
    println(a.asList())
}

/*//Genérico
fun <z>selectionSort(a:Array<E>, left:Int, right:Int, comp:(a:E, b:E)->Int){
    var min:Int
    for (j in left until right){ // os últimos dois já estão organizados
        min = j
        for(i in j+1..right){
            if (comp(a[i], a[min])<0) min = i
        }
        exch(a, j, min)
    }
}*/
/*
No searchAlgorithms.main:
val a = arrayOf("Ana", "Maria", "Carlos", "Maria", "Beatriz")
selectionSort(a, 0, 4, comp={s1, s2 -> s1.compareTo(s2)})
 */


/**
 * BubbleSort
 * n = right - left + 1
 * Complexidade temporal: teta(n^2)
 */
fun bubbleSort(a:IntArray, left:Int, right:Int){
    var switched = true
    for (j in left until right){
        if (!switched) break

        switched = false
        for (i in right downTo j+1){
            if(a[i] < a[i-1]) {
                exch(a, i, i-1)
                switched = true
            }
        }
    }
}

fun main() {
    val a = intArrayOf(3, 3, 3, 2, 3, 1)
    selectionSort(a, 0, 5)
    selectionSortStable(a, 0, 5)
}





























