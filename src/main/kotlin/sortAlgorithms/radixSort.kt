package sortAlgorithms


/**
 * Time Complexity: O(n)
 */
fun radixSort(a: Array<String?>): Array<String?> {
    if (a.isEmpty()) return a
    var new = a.copyOf(a.size)
    val stringLen = a[0]!!.length
    for (i in stringLen-1 downTo 0){
        new = countingInternalSort(new, i)
    }
    return new
}

/**
 * Time Complexity: Θ(n)
 */
fun countingInternalSort(a:Array<String?>, pos:Int): Array<String?>{
    val chars = IntArray(256)
    val sortedArray = arrayOfNulls<String>(a.size)

    for (i in a.indices) chars[a[i]!![pos].toInt()]++
    for (j in 1..255) chars[j] += chars[j-1]
    for (j in a.lastIndex downTo 0) {
        val elem = a[j]
        sortedArray[chars[elem!![pos].toInt()]-1] = elem
        chars[elem[pos].toInt()]--
    }
    return sortedArray
}


fun main() {
    var a = arrayOf<String?>("blo", "ple", "aed", "cdi", "abc", "scp")
    a = radixSort(a)
    println(a.asList())
}
