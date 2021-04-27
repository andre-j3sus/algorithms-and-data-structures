package searchAlgorithms


private fun sequentialSearch(a: IntArray, elem: Int): Int{
    var idx = -1
    for (i in a.indices){
        if (a[i] == elem) idx = i
    }
    return idx
}

fun main() {
    val a = intArrayOf(0,1,2,3,4,5,26,7,8,14,72)
    println(sequentialSearch(a, -2))
    println(sequentialSearch(a, 26))
}
