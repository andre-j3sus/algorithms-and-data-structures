package dataStructures


import randomAlgorithms.exchange


/**
 * This file contains some Heap algorithms like heapify and buildHeap.
 *
 * A Binary Heap is a Binary Tree with following properties:
 * 1) It’s a complete tree (All levels are completely filled except possibly the last level and the last level has all keys as left as possible).
 * 2) A Binary Heap is either Min Heap or Max Heap:
 *      - In a Min Binary Heap, the key at root must be minimum among all keys present in Binary Heap.
 *      - The same property must be recursively true for all nodes in Binary Tree. Max Binary Heap is similar to MinHeap.
 */


fun left(i: Int) = i * 2 + 1
fun right(i: Int) = i * 2 + 2
fun parent(i: Int) = (i - 1) / 2


/**
 * MinHeapify algorithm in IntArray.
 *
 * Time Complexity: O(log₂n)
 */
fun minHeapify(a: IntArray, root: Int, size: Int) {
    val l = left(root)
    val r = right(root)
    var smallest = root

    if (l < size && a[l] < a[smallest]) smallest = l
    if (r < size && a[r] < a[smallest]) smallest = r

    if (smallest == root) return
    exchange(a, root, smallest)
    minHeapify(a, smallest, size)
}


/**
 * MinHeapify algorithm in generic array.
 *
 * Time Complexity: O(log₂n)
 */
fun <T> minHeapify(a: Array<T>, rootIdx: Int, n: Int, cmp: Comparator<T>) {
    val l = left(rootIdx)
    val r = right(rootIdx)
    var smallest = rootIdx

    if (l < n && cmp.compare(a[l], a[smallest]) < 0) smallest = l
    if (r < n && cmp.compare(a[r], a[smallest]) < 0) smallest = r

    if (smallest == rootIdx) return
    exchange(a, rootIdx, smallest)
    minHeapify(a, smallest, n, cmp)
}


/**
 * MaxHeapify algorithm in IntArray.
 *
 * Time Complexity: O(log₂n)
 */
fun maxHeapify(a: IntArray, rootIdx: Int, n: Int) {
    val l = left(rootIdx)
    val r = right(rootIdx)
    var largest = rootIdx

    if (l < n && a[l] > a[largest]) largest = l
    if (r < n && a[r] > a[largest]) largest = r

    if (largest == rootIdx) return
    exchange(a, rootIdx, largest)
    maxHeapify(a, largest, n)
}


/**
 * MaxHeapify algorithm in generic array.
 *
 * Time Complexity: O(log₂n)
 */
fun <T> maxHeapify(a: Array<T>, rootIdx: Int, n: Int, cmp: Comparator<T>) {
    val l = left(rootIdx)
    val r = right(rootIdx)
    var largest = rootIdx

    if (l < n && cmp.compare(a[l], a[largest]) > 0) largest = l
    if (r < n && cmp.compare(a[r], a[largest]) > 0) largest = r

    if (largest == rootIdx) return
    exchange(a, rootIdx, largest)
    maxHeapify(a, largest, n, cmp)
}


/**
 * BuildMinHeap algorithm in IntArray.
 *
 * Time Complexity: O(n)
 */
fun buildMinHeap(a: IntArray) {
    var parent = parent(a.lastIndex)
    while (parent >= 0) {
        minHeapify(a, parent, a.size)
        parent--
    }
}


/**
 * BuildMaxHeap algorithm in IntArray.
 *
 * Time Complexity: O(n)
 */
fun buildMaxHeap(a: IntArray) {
    var parent = parent(a.lastIndex)
    while (parent >= 0) {
        maxHeapify(a, parent, a.size)
        parent--
    }
}


/**
 * BuildMaxHeap algorithm in generic array.
 *
 * Time Complexity: O(n)
 */
fun <T> buildMaxHeap(a: Array<T>, cmp: Comparator<T>) {
    for (i in a.size / 2 - 1 downTo 0) {
        maxHeapify(a, i, a.size, cmp)
    }
}


/**
 * BuildMinHeap algorithm in generic array.
 *
 * Time Complexity: O(n)
 */
fun <T> buildMinHeap(a: Array<T>, cmp: Comparator<T>) {
    for (i in a.size / 2 - 1 downTo 0) {
        minHeapify(a, i, a.size, cmp)
    }
}
