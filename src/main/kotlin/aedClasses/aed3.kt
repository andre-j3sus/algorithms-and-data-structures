/*
AULA 3 - 23/3/2021
-Merge sort
-Binary Search
 */

/**
 * Merge -> juntar dois arrays ordenados de modo crescentenum único array também de modo crescente
 * Complexidade temporal -> teta(n)
 * n = array.size
 */
fun merge1(array:IntArray, left:Int, leftArray:IntArray, rightArray:IntArray){
    var iLeft = 0
    var iRight = 0
    var iA = left

    while(iLeft < leftArray.size && iRight < rightArray.size) {
        if(leftArray[iLeft] < rightArray[iRight])
            array[iA] = leftArray[iLeft++]
        else
            array[iA] = rightArray[iRight++]
        iA++
    }
    while(iLeft < leftArray.size)
        array[iA++] = leftArray[iLeft++]

    while (iRight < rightArray.size)
        array[iA++] = rightArray[iRight++]
}

/**
 * MergeSort -> dividir para conquistar
 * -> para resolver o problema divide o array em partes, resolve primeiro as partes e depois junta a solução das mesmas
 * Complexidade temporal: teta(n*log2(n)) -> linearitmica -> a melhor complexidade q se pode ter
 */
fun mergeSort(a:IntArray, left: Int, right:Int){
    if(left < right) {
        val mid = left + (right-left)/2
        mergeSort(a, left, mid)
        mergeSort(a, mid+1, right)
        merge2(a, left, mid, right)
    }
}

fun merge2(a:IntArray, left:Int, mid:Int, right:Int){
    val leftArray = IntArray(mid-left+1)
    val rightArray = IntArray(right-mid)

    for(i in left..mid) leftArray[i-left]=a[i]
    for(i in mid+1..right) rightArray[i-mid-1]=a[i]

    merge1(a, left, leftArray, rightArray)
}


/**
 * Binary Search
 */
private fun binarySearchR(a:IntArray, l: Int, r: Int, elem:Int) :Int{
    if (l > r) return -1

    val mid = l + (r-l)/2
    return when{
        a[mid] == elem -> mid
        elem < a[mid] -> binarySearchR(a, l, mid-1, elem)
        else -> binarySearchR(a, mid+1, r, elem)
    }
}

//TODO:: encontrar o primeiro elemento -> lowerBound
private fun binarySearchI(a:IntArray, left: Int, right: Int, elem:Int) :Int{
    var l = left
    var r = right

    while (l <= r){
        val mid = l + (r-l)/2
        when {
            a[mid] == elem -> return mid
            elem < a[mid] -> r = mid -1
            else -> l = mid + 1
        }
    }
    return -1
}


private fun binarySearchILowerBound(a:IntArray, left: Int, right: Int, elem:Int) :Int{
    var l = left
    var r = right

    while (l < r){
        val mid = l + (r-l)/2

        if(elem <= a[mid]) r = mid
        else l = mid + 1
    }
    if (a[l] == elem) return l

    return -1
}




fun main() {
    val a = intArrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    mergeSort(a, 0, a.lastIndex)
    println(a.asList())
}










