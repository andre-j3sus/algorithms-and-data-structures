package sortAlgorithms

import randomAlgorithms.exchange


/**
 * Selection Sort of an IntArray.
 *
 * Time Complexity: O(n²)
 * @param a array
 */
fun selectionSort(a: IntArray) {
    var currentIdx = 0
    for (i in a.indices) {
        exchange(a, currentIdx++, minIdx(a, i, a.lastIndex))
    }
}


/**
 * Selection Sort of a generic array.
 *
 * Time Complexity: O(n²)
 * @param a array
 * @param cmp generic comparator
 */
fun <T> selectionSort(a: Array<T>, cmp: Comparator<T>) {
    for (i in a.indices) {
        exchange(a, i, minIdx(a, i, a.size - 1, cmp))
    }
}


/**
 * Returns the index of the minimum element of an IntArray in a certain range.
 *
 * Time Complexity: O(n)
 * @param a array
 * @param l minimum index
 * @param r maximum index
 */
fun minIdx(a: IntArray, l: Int, r: Int): Int {
    var minIdx = l
    for (i in l..r) {
        if (a[i] < a[minIdx]) minIdx = i
    }
    return minIdx
}


/**
 * Returns the index of the minimum element of a generic array in a certain range.
 *
 * Time Complexity: O(n)
 * @param a array
 * @param l minimum index
 * @param r maximum index
 * @param cmp generic comparator
 */
fun <T> minIdx(a: Array<T>, l: Int, r: Int, cmp: Comparator<T>): Int {
    var minIdx = l
    for (i in l..r) {
        if (cmp.compare(a[i], a[minIdx]) < 0) minIdx = i
    }
    return minIdx
}


/**
 * Test function of selection sort algorithm.
 */
fun main() {
    val a1 = intArrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    selectionSort(a1)
    println(a1.asList()) // [-20, -9, -3, -2, 0, 2, 4, 5, 14, 19]

    val a2 = arrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    selectionSort(a2) { o1, o2 -> o1 - o2 }
    println(a2.asList()) // [-20, -9, -3, -2, 0, 2, 4, 5, 14, 19]
}
