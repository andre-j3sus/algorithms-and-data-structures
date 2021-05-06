package sortAlgorithms

/**
 * Time Complexity: O(n)
 */
private fun merge(newArray: IntArray, l:Int, a:IntArray, b:IntArray){
    var aIdx = 0
    var bIdx = 0
    var currentIdx = l

    while (aIdx < a.size && bIdx < b.size){
        newArray[currentIdx++] = if (a[aIdx] <= b[bIdx]) a[aIdx++] else b[bIdx++]
    }

    while (aIdx < a.size){
        newArray[currentIdx++] = a[aIdx++]
    }

    while (bIdx < b.size){
        newArray[currentIdx++] = b[bIdx++]
    }
}


/**
 * Time Complexity: O(n)
 */
private fun mergeLeftAndRight(a:IntArray, l:Int, mid:Int, r:Int){
    val leftArray = a.copyOfRange(l, mid+1) // toIndex -> exclusive
    val rightArray = a.copyOfRange(mid+1, r+1)
    merge(a, l, leftArray, rightArray)
}


/**
 * Time Complexity: O(nlog₂n)
 */
private fun mergeSort(a: IntArray, l: Int, r: Int){
    if (l < r){
        val mid = l + (r-l)/2
        mergeSort(a, l, mid)
        mergeSort(a, mid+1, r)
        mergeLeftAndRight(a, l, mid, r) // O(n)
    }
}

fun main() {
    val a = intArrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    mergeSort(a, 0, a.lastIndex)
    println(a.asList())
}
