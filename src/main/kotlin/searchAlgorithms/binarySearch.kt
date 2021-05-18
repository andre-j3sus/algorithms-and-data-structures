package searchAlgorithms

fun binarySearchR(a:IntArray, elem:Int, left:Int, right:Int):Int{
    if(left>right) return -1

    val mid = left + (right-left)/2
    return when {
        elem == a[mid] -> mid
        elem < a[mid]  -> binarySearchR(a, elem, left, mid-1)
        else           -> binarySearchR(a, elem, mid+1, right)
    }
}

fun binarySearch(a:IntArray, elem:Int):Int{
    var left = 0
    var right = a.size - 1

    while (left < right){
        val mid = left + (right-left)/2
        when {
            a[mid] == elem -> return mid
            elem < a[mid]  -> right = mid-1
            else           -> left = mid+1
        }
    }
    return -1
}

fun <T> binarySearch(a: Array<T>, elem:T, cmp:Comparator<T>, l: Int, r: Int): Int{
    if (l > r) return -1
    val mid = l + (r - l)/2
    return when{
        cmp.compare(a[mid], elem) == 0 -> mid
        cmp.compare(a[mid], elem) < 0  -> binarySearch(a, elem, cmp, mid+1, r)
        else                           -> binarySearch(a, elem, cmp, l, mid-1)
    }
}

fun main() {
    val a = intArrayOf(0,1,2,3,4,5,6,7,8,14,26,72)
    println(binarySearch(a, 26))
    println(binarySearchR(a, 26, 0, a.lastIndex))

    var a2 = arrayOf(0,1,2,3,4,5,6,7,8,14,26,72)
    println(binarySearch(a2, 26, {a, b -> a - b}, 0, a.lastIndex))
}
