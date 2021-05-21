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


fun buildMinHeap(a: IntArray){
    var parent = parent(a.lastIndex)
    while (parent >= 0){
        minHeapify(a, parent, a.size)
        parent--
    }

}
