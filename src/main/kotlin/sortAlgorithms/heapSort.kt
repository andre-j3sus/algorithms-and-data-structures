package sortAlgorithms

import dataStructures.*
import randomAlgorithms.exchange


/**
 * Heap Sort of an IntArray.
 *
 * Time Complexity: O(nlog₂n)
 * @param heap array
 */
fun heapSort(heap: IntArray) {
    buildMaxHeap(heap)
    for (i in heap.size - 1 downTo 1) {
        exchange(heap, 0, i)
        maxHeapify(heap, 0, i)
    }
}


/**
 * Heap Sort of a generic array.
 *
 * Time Complexity: O(nlog₂n)
 * @param heap array
 * @param size array size
 * @param cmp generic comparator
 */
fun <T> heapSort(heap: Array<T>, size: Int = heap.size, cmp: Comparator<T>) {
    buildMaxHeap(heap, cmp)
    for (i in size - 1 downTo 1) {
        exchange(heap, 0, i)
        maxHeapify(heap, 0, i, cmp)
    }
}


/**
 * Test function of heap sort algorithm.
 */
fun main() {
    val a1 = intArrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    heapSort(a1)
    println(a1.asList()) // [-20, -9, -3, -2, 0, 2, 4, 5, 14, 19]


    val a2 = arrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    heapSort(a2) { o1, o2 -> o1 - o2 }
    println(a2.asList()) // [-20, -9, -3, -2, 0, 2, 4, 5, 14, 19]
}
