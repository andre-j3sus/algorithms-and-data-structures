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


fun <T> minIdx(a:Array<T>, l:Int, r:Int, cmp:Comparator<T>):Int{
    var minIdx = l
    for (i in l..r){
        if (cmp.compare(a[i], a[minIdx]) < 0) minIdx = i
    }
    return minIdx
}


fun <T> selectionSort(a: Array<T>, cmp:Comparator<T>){
    for (i in a.indices){
        val minIdx = minIdx(a, i, a.size-1, cmp)
        exchange(a, i, minIdx)
    }
}


fun main() {
    val a = intArrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    selectionSort(a)
    println(a.asList())

    val a2 = arrayOf<Int>(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    selectionSort(a2) { o1, o2 -> o1 - o2 }
    println(a2.asList())
}
