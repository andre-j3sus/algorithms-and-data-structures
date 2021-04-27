package searchAlgorithms

private fun binarySearchR(a:IntArray, elem:Int, left:Int, right:Int):Int{
    if(left>right) return -1

    val mid = left + (right-left)/2
    return when {
        elem == a[mid] -> mid
        elem < a[mid] -> binarySearchR(a, elem, left, mid-1)
        else          -> binarySearchR(a, elem, mid+1, right)
    }
}

private fun binarySearch(a:IntArray, elem:Int):Int{
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


fun main() {
    val a = intArrayOf(0,1,2,3,4,5,26,7,8,14,72)
    println(binarySearch(a, a.lastIndex))
    println(binarySearchR(a, a.lastIndex, 0, 11))
}
