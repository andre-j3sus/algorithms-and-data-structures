package sortAlgorithms

import randomAlgorithms.exchange

/**
 * Hoare Partition Scheme
 *
 * Time Complexity: Θ(n)
 */
fun partition(a:IntArray, left:Int, right:Int): Int{
    var i = left - 1
    var j = right
    val pivot = a[right]
    while (true){
        while (i < right && a[++i] < pivot);
        while (j > left && a[--j] > pivot);
        if (j == left || i >= j) break
        exchange(a, i, j)
    }
    exchange(a, i, right)
    return i
}


/**
 * Time Complexity:
 * - Best case: Ω(nlog₂(n))
 * - Worst case: O(n²)
 */
fun quickSort(a: IntArray, left:Int, right:Int){
    if (left > right) return
    val i = partition(a, left, right)
    quickSort(a, left, i - 1)
    quickSort(a, i + 1, right)
}


fun main() {
    val a = intArrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    partition(a, 0, a.lastIndex)
    println(a.asList())
    /*quickSort(a, 0, a.lastIndex)
    println(a.asList())*/
}
