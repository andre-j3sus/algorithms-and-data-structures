package sortAlgorithms

import randomAlgorithms.exchange


/**
 * Hoare Partition Scheme.
 *
 * Time Complexity: Θ(n)
 * @param a array
 * @param left left index
 * @param right right index
 */
fun partition(a: IntArray, left: Int, right: Int): Int {
    var i = left - 1
    var j = right
    val pivot = a[right]
    while (true) {
        while (i < right && a[++i] < pivot);
        while (j > left && a[--j] > pivot);
        if (j == left || i >= j) break
        exchange(a, i, j)
    }
    exchange(a, i, right)
    return i
}


/**
 * Quick Sort of an IntArray.
 *
 * Time Complexity: O(nlog₂n)
 * @param a array
 * @param left left index
 * @param right right index
 */
fun quickSort(a: IntArray, left: Int, right: Int) {
    if (left > right) return
    val i = partition(a, left, right)
    quickSort(a, left, i - 1)
    quickSort(a, i + 1, right)
}


/**
 * Test function of quick sort algorithm.
 */
fun main() {
    val a = intArrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)

    quickSort(a, 0, a.lastIndex)
    println(a.asList()) // [-20, -9, -3, -2, 0, 2, 4, 5, 14, 19]
}
