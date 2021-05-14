package sortAlgorithms

import randomAlgorithms.exchange


/**
 * Time Complexity: O(n²)
 */
fun selectionSort(a: IntArray){
    var currentIdx = 0

    for (i in a.indices){
        exchange(a, currentIdx++, minIdx(a, i, a.lastIndex))
    }
}


/**
 * Time Complexity: O(n²)
 */
fun minIdx(a:IntArray, l:Int, r:Int):Int{
    var minIdx = l
    for (i in l..r){
        if (a[i] < a[minIdx]) minIdx = i
    }
    return minIdx
}


fun main() {
    val a = intArrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    selectionSort(a)
    println(a.asList())
}
