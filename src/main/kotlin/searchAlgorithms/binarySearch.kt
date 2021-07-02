package searchAlgorithms


/**
 * Binary Search recursive of an element in a IntArray.
 *
 * Time Complexity: O(log₂n)
 * @param a array
 * @param elem element to search
 * @param left left index
 * @param right right index
 * @return index of the element
 */
fun binarySearchR(a: IntArray, elem: Int, left: Int, right: Int): Int {
    if (left > right) return -1

    val mid = left + (right - left) / 2
    return when {
        elem == a[mid] -> mid
        elem < a[mid]  -> binarySearchR(a, elem, left, mid - 1)
        else           -> binarySearchR(a, elem, mid + 1, right)
    }
}


/**
 * Binary Search iterative of an element in a IntArray.
 *
 * Time Complexity: O(log₂n)
 * @param a array
 * @param elem element to search
 * @return index of the element
 */
fun binarySearch(a: IntArray, elem: Int): Int {
    var left = 0
    var right = a.size - 1

    while (left < right) {
        val mid = left + (right - left) / 2
        when {
            a[mid] == elem -> return mid
            elem < a[mid]  -> right = mid - 1
            else           -> left = mid + 1
        }
    }
    return -1
}


/**
 * Binary Search recursive of an element in a generic array.
 *
 * Time Complexity: O(log₂n)
 * @param a array
 * @param elem element to search
 * @param cmp generic comparator
 * @param l left index
 * @param r right index
 * @return index of the element
 */
fun <T> binarySearch(a: Array<T>, elem: T, cmp: Comparator<T>, l: Int, r: Int): Int {
    if (l > r) return -1
    val mid = l + (r - l) / 2
    return when {
        cmp.compare(a[mid], elem) == 0 -> mid
        cmp.compare(a[mid], elem) < 0  -> binarySearch(a, elem, cmp, mid + 1, r)
        else                           -> binarySearch(a, elem, cmp, l, mid - 1)
    }
}


/**
 * Test function of binary search algorithm.
 */
fun main() {
    val a1 = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 14, 26, 72)
    println(binarySearch(a1, 26)) // 10
    println(binarySearchR(a1, 26, 0, a1.lastIndex)) // 10

    val a2 = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 14, 26, 72)
    println(binarySearch(a2, 26, { a, b -> a - b }, 0, a1.lastIndex)) // 10
}
