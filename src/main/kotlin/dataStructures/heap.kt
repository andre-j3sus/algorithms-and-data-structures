package dataStructures

import randomAlgorithms.exchange


fun left(i:Int) = i*2 + 1
fun right(i:Int) = i*2 + 2
fun parent(i:Int) = (i - 1)/2


fun minHeapify(a: IntArray, root: Int, size: Int){
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


fun buildMinHeap(a: IntArray){
    var parent = parent(a.lastIndex)
    while (parent >= 0){
        minHeapify(a, parent, a.size)
        parent--
    }
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

