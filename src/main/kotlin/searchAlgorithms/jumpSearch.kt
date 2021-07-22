package searchAlgorithms

import java.lang.Integer.min
import kotlin.math.floor
import kotlin.math.sqrt


/**
 * Jump Search of an element in a IntArray.
 *
 * Time Complexity: O(√n)
 * @param a array
 * @param elem element to search
 * @return index of the element
 */
fun jumpSearch(a: IntArray, elem: Int): Int {
    val n = a.size
    val jump = floor(sqrt(n.toDouble())).toInt()
    var m = jump

    var i = 0
    while (a[min(m, n) - 1] < elem) {
        i = m
        m += jump
        if (i >= n) return -1
    }

    while (a[i] < elem) {
        if (++i == min(m, n)) break
        if (a[i] == elem) return i
    }

    return -1
}


/**
 * Jump Search of an element in a generic array.
 *
 * Time Complexity: O(√n)
 * @param a array
 * @param elem element to search
 * @param cmp generic comparator
 * @return index of the element
 */
fun <T> jumpSearch(a: Array<T>, elem: T, cmp: Comparator<T>): Int {
    val n = a.size
    val jump = floor(sqrt(n.toDouble())).toInt()
    var m = jump

    var i = 0
    while (cmp.compare(a[min(m, n) - 1], elem) < 0) {
        i = m
        m += jump
        if (i >= n) return -1
    }

    while (cmp.compare(a[i], elem) < 0) {
        if (++i == min(m, n)) break
        if (a[i] == elem) return i
    }

    return -1
}


/**
 * Test function of jump search algorithm.
 */
fun main() {
    val a1 = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 14, 26, 72)
    println(jumpSearch(a1, 26)) // 10
    println(jumpSearch(a1, 27)) // -1

    val a2 = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 14, 26, 72)
    println(jumpSearch(a2, 72) { a, b -> a - b }) // 11
}
