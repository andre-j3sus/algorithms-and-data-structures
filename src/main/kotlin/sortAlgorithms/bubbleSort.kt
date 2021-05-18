package sortAlgorithms

import randomAlgorithms.exchange


/**
 * Time Complexity: O(n²)
 */
fun bubbleSort(a: IntArray){
    var switched = true

    for (i in a.indices){
        if (!switched) break
        switched = false

        for (j in a.lastIndex downTo i + 1){
            if (a[j] < a[j-1]) {
                exchange(a, j, j-1)
                switched = true
            }
        }
    }
}


fun <T> bubbleSort(a: Array<T>, cmp:Comparator<T>){
    var swapped: Boolean

    for (i in a.size - 1 downTo 0){
        swapped = false
        for (j in 0 until i){
            if (cmp.compare(a[j], a[j+1]) > 0){
                exchange(a, j, j+1)
                swapped = true
            }
        }
        if (!swapped) return
    }
}


fun main() {
    val a = intArrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    bubbleSort(a)
    println(a.asList())

    val a2 = arrayOf<Int>(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    bubbleSort(a2) { o1, o2 -> o1 - o2 }
    println(a2.asList())
}
