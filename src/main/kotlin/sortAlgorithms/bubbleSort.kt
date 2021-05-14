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



fun main() {
    val a = intArrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    bubbleSort(a)
    println(a.asList())
}
