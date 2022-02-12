package sortAlgorithms


/**
 * Insertion Sort of an IntArray.
 *
 * Time Complexity: O(n²)
 * @param a array
 */
fun insertionSort(a: IntArray) {
    for (i in 1 until a.size) {
        val value = a[i]
        var currentIdx = i

        while (currentIdx > 0 && a[currentIdx - 1] > value) {
            a[currentIdx] = a[--currentIdx]
        }
        a[currentIdx] = value
    }
}


/**
 * Insertion Sort of a generic Array.
 *
 * Time Complexity: O(n²)
 * @param a array
 * @param cmp generic comparator
 */
fun <T> insertionSort(a: Array<T>, cmp: Comparator<T>) {
    for (i in 1 until a.size) {
        val value = a[i]
        var curIdx = i

        while (curIdx > 0 && cmp.compare(a[curIdx - 1], value) > 0) {
            a[curIdx] = a[--curIdx]
        }
        a[curIdx] = value
    }
}


/**
 * Test function of insertion sort algorithm.
 */
fun main() {
    val a1 = intArrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    insertionSort(a1)
    println(a1.asList()) // [-20, -9, -3, -2, 0, 2, 4, 5, 14, 19]

    val a2 = arrayOf<Int>(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    insertionSort(a2) { o1, o2 -> o1 - o2 }
    println(a2.asList()) // [-20, -9, -3, -2, 0, 2, 4, 5, 14, 19]
}
