package sortAlgorithms

/**
 * Time Complexity: O(nlog₂n)
 */
fun heapSort(heap: IntArray){
    val n = heap.size
    buildMaxHeap(heap)
    for (i in n-1 downTo 1){
        exchange(heap, 0, i)
        maxHeapify(heap, 0, i)
    }
}

fun left(i:Int) = i*2 + 1
fun right(i:Int) = i*2 + 2
fun parent(i:Int) = (i - 1)/2


/**
 * Time Complexity: O(log₂n)
 */
fun maxHeapify(a: IntArray, rootIdx: Int, n: Int){
    val l = left(rootIdx)
    val r = right(rootIdx)
    var largest = rootIdx

    if(l < n && a[l] > a[largest]) largest = l
    if(r < n && a[r] > a[largest]) largest = r

    if (largest == rootIdx) return
    exchange(a, rootIdx, largest)
    maxHeapify(a, largest, n)
}

/**
 * Time Complexity: O(n)
 */
fun buildMaxHeap(a: IntArray){
    var parent = parent(a.lastIndex)
    while (parent >= 0){
        maxHeapify(a, parent, a.size)
        parent--
    }
}


fun main() {
    val a = intArrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    heapSort(a)
    println(a.asList())
}
