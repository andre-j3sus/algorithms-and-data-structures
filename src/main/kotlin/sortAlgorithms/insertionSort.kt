package sortAlgorithms


/**
 * Time Complexity: O(n²)
 */
fun insertionSort(a: IntArray){
    for (i in 1 until a.size){
        val value = a[i]
        var currentIdx = i

        while (currentIdx > 0 && a[currentIdx - 1] > value){
            a[currentIdx] = a[--currentIdx]
        }
        a[currentIdx] = value
    }
}


fun <T> insertionSort(a: Array<T>, cmp: Comparator<T>){
    for (i in 1 until a.size){
        val value = a[i]
        var curIdx = i
        while (curIdx > 0 && cmp.compare(a[curIdx - 1], value) > 0){
            a[curIdx] = a[--curIdx]
        }
        a[curIdx] = value
    }
}



fun main() {
    val a = intArrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    insertionSort(a)
    println(a.asList())

    val a2 = arrayOf<Int>(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    insertionSort(a2) { o1, o2 -> o1 - o2 }
    println(a2.asList())
}
