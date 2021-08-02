package sortAlgorithms

import otherAlgorithms.exchange


/**
 * Bubble Sort of an IntArray.
 *
 * Time Complexity: O(n²)
 * @param a array
 */
fun bubbleSort(a: IntArray) {
    var switched = true

    for (i in a.indices) {
        if (!switched) break
        switched = false

        for (j in a.lastIndex downTo i + 1) {
            if (a[j] < a[j - 1]) {
                exchange(a, j, j - 1)
                switched = true
            }
        }
    }
}


/**
 * Bubble Sort of a generic array.
 *
 * Time Complexity: O(n²)
 * @param a array
 * @param cmp generic comparator
 */
fun <T> bubbleSort(a: Array<T>, cmp: Comparator<T>) {
    var swapped: Boolean

    for (i in a.size - 1 downTo 0) {
        swapped = false

        for (j in 0 until i) {
            if (cmp.compare(a[j], a[j + 1]) > 0) {
                exchange(a, j, j + 1)
                swapped = true
            }
        }
        if (!swapped) return
    }
}


/**
 * Test function of bubble sort algorithm.
 */
fun main() {
    val a1 = intArrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    bubbleSort(a1)
    println(a1.asList()) // [-20, -9, -3, -2, 0, 2, 4, 5, 14, 19]

    val a2 = arrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    bubbleSort(a2) { o1, o2 -> o1 - o2 }
    println(a2.asList()) // [-20, -9, -3, -2, 0, 2, 4, 5, 14, 19]
}
