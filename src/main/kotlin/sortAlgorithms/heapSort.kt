package sortAlgorithms

import randomAlgorithms.exchange

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

fun <T> heapSort(heap: Array<T>, size:Int = heap.size, cmp: Comparator<T>){
    buildMaxHeap(heap, cmp)
    for (i in size-1 downTo 1){
        exchange(heap, 0, i)
        maxHeapify(heap, 0, i, cmp)
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


fun <T> maxHeapify(a: Array<T>, rootIdx: Int, n: Int, cmp: Comparator<T>){
    val l = left(rootIdx)
    val r = right(rootIdx)
    var largest = rootIdx

    if(l < n && cmp.compare(a[l],a[largest]) > 0) largest = l
    if(r < n && cmp.compare(a[r],a[largest]) > 0) largest = r

    if (largest == rootIdx) return
    exchange(a, rootIdx, largest)
    maxHeapify(a, largest, n, cmp)
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


fun <T> buildMaxHeap(a: Array<T>, cmp: Comparator<T>){
    for (i in a.size / 2 - 1 downTo 0){
        maxHeapify(a, i, a.size, cmp)
    }
}


fun main() {
    val a = intArrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    heapSort(a)
    println(a.asList())


    val a2 = arrayOf<Int>(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    heapSort(a2) { o1, o2 -> o1 - o2 }
    println(a2.asList())
}
