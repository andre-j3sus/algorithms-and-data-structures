package searchAlgorithms


/**
 * Sequential search of an element in a IntArray.
 *
 * Time Complexity: O(n)
 * @param a array
 * @param elem element to search
 * @return index of the element
 */
fun sequentialSearch(a: IntArray, elem: Int): Int{
    var idx = -1
    for (i in a.indices){
        if (a[i] == elem) idx = i
    }
    return idx
}


/**
 * Sequential search of an element in a generic array.
 *
 * Time Complexity: O(n)
 * @param a array
 * @param elem element to search
 * @return index of the element
 */
fun <T> sequentialSearch(a: Array<T>, elem: T): Int{
    var idx = -1
    for (i in a.indices){
        if (a[i] == elem) idx = i
    }
    return idx
}


/**
 * Test function of the sequential search algorithm.
 */
fun main() {
    val a1 = intArrayOf(0,1,2,3,4,5,26,7,8,14,72)
    println(sequentialSearch(a1, -2)) // -1
    println(sequentialSearch(a1, 26)) // 6


    val a2 = arrayOf(0,1,2,3,4,5,26,7,8,14,72)
    println(sequentialSearch(a2, 26)) // 6
}
